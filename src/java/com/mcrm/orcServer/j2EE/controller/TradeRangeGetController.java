/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.orcServer.j2EE.controller;

import com.mcrm.orcServer.OrcServer;
import com.mcrm.orcServer.factory.OrcServerFactory;
import com.mcrm.orcServer.message.enums.Kind;
import com.mcrm.orcServer.message.enums.Market;
import java.io.Serializable;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Chris.Yu
 */
@ManagedBean
@SessionScoped
public class TradeRangeGetController implements Serializable{
    Date startDatetime;
    Date endDatetime;
    Kind selectedKind;
    Kind [] kinds;
    Market selectedMarket;
    Market [] markets;
    OrcServer orcServer;
    private static final long serialVersionUID = 2L;
    public TradeRangeGetController() throws SocketException {
        //orcServer = OrcServerFactory.createOrcServer();
    }
//   static{
//    orcServer = OrcServerFactory.createOrcServer();
//   }
    public OrcServer getOrcServer() {
        return orcServer;
    }
    
    public Market getSelectedMarket() {
        return selectedMarket;
    }

    public void setSelectedMarket(Market selectedMarket) {
        this.selectedMarket = selectedMarket;
    }

     
    public Market[] getMarkets() {
        return Market.values();
    }

    public void setMarkets(Market[] markets) {
        this.markets = markets;
    }
    

    public Kind getSelectedKind() {
        return selectedKind;
    }

    public void setSelectedKind(Kind selectedKind) {
        this.selectedKind = selectedKind;
    }

    public Kind[] getKinds() {
        return Kind.values();
    }

    public void setKinds(Kind[] kinds) {
        this.kinds = kinds;
    }
    
    

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }
    
    //call the business logic
    public String sendTradeRangeGetMessage() throws SocketException{
        Calendar startC = Calendar.getInstance();
        startC.setTime(startDatetime);
        Calendar endC = Calendar.getInstance();
        endC.setTime(endDatetime);
        return orcServer.sendTradeRangeGetMessage(startC, endC,selectedMarket, selectedKind);
    }
    
}
