/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.orcServer.j2EE.Exception;

import java.net.SocketException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

/**
 *
 * @author Chris.Yu
 */
public class OrcServerExceptionHandler extends ExceptionHandlerWrapper{
    private static final Logger LOGGER;
    private final javax.faces.context.ExceptionHandler wrapped;
    
    static{
       LOGGER=Logger.getLogger(OrcServerExceptionHandler.class.getName());
    } 
    public OrcServerExceptionHandler(javax.faces.context.ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }
    @Override
    public javax.faces.context.ExceptionHandler getWrapped() {
        return this.wrapped;
    }
    
 @Override
 public void handle() throws FacesException { 
  for (final Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) { 
   Throwable t = it.next().getContext().getException(); 
   while ((t instanceof FacesException || t instanceof EJBException || t instanceof ELException) 
     && t.getCause() != null) { 
    t = t.getCause(); 
   } 
   if (t instanceof SocketException ){ 
    final FacesContext facesContext = FacesContext.getCurrentInstance(); 
    final ExternalContext externalContext = facesContext.getExternalContext(); 
    final Map<String, Object> requestMap = externalContext.getRequestMap(); 
    try { 
     LOGGER.log(Level.SEVERE,"Caught on the view layer");
     String message; 
//     if (t instanceof ViewExpiredException) { 
//      final String viewId = ((ViewExpiredException) t).getViewId(); 
//      message = "View is expired. <a href='/ifos"   viewId   "'>Back</a>"; 
//     } else { 
//      message = t.getMessage(); // beware, don't leak internal info! 
//     } 
     message=t.getMessage();
     requestMap.put("errorMsg", message); 
     try { 
      externalContext.dispatch("/error.jsp"); 
     } catch (final Exception e) { 
      LOGGER.log(Level.SEVERE,"Error view '/error.jsp' unknown!",e); 
     } 
     facesContext.responseComplete(); 
    } finally { 
     it.remove(); 
    } 
   } 
  }//for 
  getWrapped().handle(); 
 } 
  

    
    
}
