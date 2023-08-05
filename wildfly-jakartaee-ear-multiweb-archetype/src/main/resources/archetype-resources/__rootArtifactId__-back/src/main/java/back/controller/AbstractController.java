#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.util.ResourceBundle;

public abstract class AbstractController {

    @Inject
    private FacesContext context;

    protected FacesContext getContext() {
        return context;
    }

    protected ResourceBundle getBundle(String bundleName) {
        return context.getApplication().getResourceBundle(context, bundleName);
    }

    protected void addGlobalMessage(String message) {
        context.addMessage(null, new FacesMessage(message));
    }

    protected void keepMessages() {
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
}