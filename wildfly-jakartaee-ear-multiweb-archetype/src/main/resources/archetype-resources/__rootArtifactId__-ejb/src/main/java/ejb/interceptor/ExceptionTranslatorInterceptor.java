#set( $symbol_pound = '#' )
        #set( $symbol_dollar = '$' )
        #set( $symbol_escape = '\' )
        package ${package}.ejb.interceptor;

import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.exception.ServiceException;

import jakarta.ejb.EJBException;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import java.io.Serializable;

/**
 * Interceptor per capturar excepcions. Permet transformar per un EJB de la capa de serveis les excepcions internes
 * amb excepcions adequades per la capa de serveis.
 * Per exemple, es podrien extreure els errors SQL per llançar excepcions apropiades a la capa de serveis.
 *
 * @author areus
 */
@ExceptionTranslate
@Interceptor
public class ExceptionTranslatorInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Transforma les excepcions.
     *
     * @param context Contexte d'invocació.
     * @return El resultat del mètode interceptar.
     * @throws Exception llança l'excepció processada.
     */
    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (PersistenceException persistenceException) {
            throw processPersistenceException(persistenceException);
        } catch (EJBException ejbException) {
            Throwable cause = ejbException.getCause();
            if (cause instanceof PersistenceException) {
                throw processPersistenceException((PersistenceException) cause);
            }
            throw ejbException;
        }
    }

    private ServiceException processPersistenceException(PersistenceException exception) {
        if (exception instanceof EntityNotFoundException) {
            return new RecursNoTrobatException();
        }
        // Si es una violació de constraints es podria extreure la SQLException per trobar el codi
        // o emprar la ConstraintViolationException de hibernate.
        return new ServiceException(exception);
    }
}