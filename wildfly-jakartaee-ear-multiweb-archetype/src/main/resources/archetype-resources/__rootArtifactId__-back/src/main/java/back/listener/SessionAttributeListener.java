#set( $symbol_pound = '#' )
        #set( $symbol_dollar = '$' )
        #set( $symbol_escape = '\' )
        package ${package}.back.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import java.io.Serializable;

/**
 * Monitoritza els attributs que s'afegeixen a la sessió, per garantir que tots són Serializables. Ficar atributs
 * no serializables a dins la sessió pot provocar problemes si l'aplicació es configura per alta disponibilitat.
 *
 * @author areus
 */
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    private static final Logger LOG = LoggerFactory.getLogger(SessionAttributeListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getValue() != null && !(event.getValue() instanceof Serializable)) {
            LOG.warn("L'atribut [" + event.getName() + "] afegit a la sessió [" + event.getSession().getId() + "] " +
                    "no és serializable");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getValue() != null && !(event.getValue() instanceof Serializable)) {
            LOG.warn("L'atribut [" + event.getName() + "] reemplaçat a la sessió [" + event.getSession().getId() + "] " +
                    "no és serializable");
        }
    }
}