/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.orcServer.j2EE.Exception;

import javax.faces.context.ExceptionHandler;


/**
 *
 * @author Chris.Yu
 */

public class OrcServerExceptionHandlerFactory extends javax.faces.context.ExceptionHandlerFactory{
    
    private javax.faces.context.ExceptionHandlerFactory parent = null;

    public OrcServerExceptionHandlerFactory(final javax.faces.context.ExceptionHandlerFactory parent) {
        this.parent=parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new OrcServerExceptionHandler(parent.getExceptionHandler());
    }
    
    
    
}
