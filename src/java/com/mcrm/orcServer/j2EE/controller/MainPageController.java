/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.orcServer.j2EE.controller;

import com.mcrm.orcServer.OrcServer;
import com.mcrm.orcServer.OrcServerModule;
import com.mcrm.orcServer.OrcServerStatus;
import com.mcrm.orcServer.factory.OrcServerFactory;
import com.mcrm.orcServer.message.enums.Kind;
import com.mcrm.orcServer.message.enums.Market;
import java.io.Serializable;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Chris.Yu
 */
@ManagedBean(name = "mainPageController")
@SessionScoped
public class MainPageController implements Serializable{
    
    private Map<String,OrcServerModule> checkerModuleMap;
    private Map<String,OrcServerModule> blotterModuleMap;
    private OrcServer orcServer;
    
    private static final long serialVersionUID = 3L;
    public MainPageController() throws SocketException {
        orcServer = OrcServerFactory.createOrcServer();
        //test modules
//        checkerModule = new ArrayList<OrcServerModule>(5);
//        blotterModule = new ArrayList<OrcServerModule>(5);
//        checkerModule.add(new OrcServerModule("Checker1", OrcServerStatus.ON, Calendar.getInstance()));
//        checkerModule.add(new OrcServerModule("Checker2", OrcServerStatus.ON, Calendar.getInstance()));
//        checkerModule.add(new OrcServerModule("Checker3", OrcServerStatus.FAILURE, Calendar.getInstance()));
//        checkerModule.add(new OrcServerModule("Checker4", OrcServerStatus.ON, Calendar.getInstance()));
//        checkerModule.add(new OrcServerModule("Checker5", OrcServerStatus.FAILURE, Calendar.getInstance()));
//        blotterModule.add(new OrcServerModule("trg 1", OrcServerStatus.FAILURE, Calendar.getInstance()));
          checkerModuleMap=orcServer.getOrcServerCheckerModule();
          blotterModuleMap=orcServer.getOrcServerBlotterModule();
    }
//   static{
//    orcServer = OrcServerFactory.createOrcServer();
//   }
    public OrcServer getOrcServer() {
        return orcServer;
    }

    public List<OrcServerModule> getCheckerModuleList() {
        return new ArrayList<OrcServerModule>(checkerModuleMap.values());
    }
    public List<OrcServerModule> getBlotterModuleList() {
        return new ArrayList<OrcServerModule>(blotterModuleMap.values());
    }
    
    
    
}
