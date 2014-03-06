/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.orcServer.j2EE.controller;

import com.mcrm.blockTrade.utility.BlockTradeUtility;
import com.mcrm.model.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This is the class that contains a web poller on a timer that polls CME public
 * block trade server
 *
 * @author Chris.Yu
 */
@Singleton
public class BlcokTradeController {
    /*
     when using the URI, the current date of the format "01152014" 
     must added in between the PREFIX and the SUFFIX
     */

    private final static String CME_BLOCK_TRADE_URI_PREFIX = "http://www.cmegroup.com/CmeWS/mvc/xsltTransformer.do?xlstDoc=/XSLT/md/blocks-records.xsl&url=/da/BlockTradeQuotes/V1/Block/BlockTrades?exchange=XNYM&foi=FUT,OPT&assetClassId=7&tradeDate=";
    private final static String CME_BLOCK_TRADE_URI_SUFFIX = "&sortCol=time&sortBy=desc&_=";
    //for each poll, Paul Walker will return a collection of different products
    private Properties properties = new Properties();

    public BlcokTradeController() {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Config/config.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BlcokTradeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlcokTradeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class PollWorker implements Callable<Set<Set<Product>>> {

        @Override
        public synchronized Set<Set<Product>> call() {
            /*
             1.) make an httprequest the public server
             2.) parse the result, create the products instances based on the parsed results
             3.) catagorize the products and sort'em by time
             4.) render the view by populating the view model(the backup bean of the jsp)
             */
            // create the URI to poll
            String bTradeURI = CME_BLOCK_TRADE_URI_PREFIX + BlockTradeUtility.getCurrentDateString() + CME_BLOCK_TRADE_URI_SUFFIX + BlockTradeUtility.getRandomThirteenDigitString();
            try {
                Document document = Jsoup.connect(bTradeURI).get();
                Elements trades = document.getElementsByClass(properties.getProperty("CMEBlockTradeClassName"));
                for (Element trade : trades) {
                    Elements attrs = trade.getElementsByIndexGreaterThan(-1);
                    for (Element attr : attrs) {
                        String value = attr.data();
                        System.out.print(value);
                        System.out.print(" ");
                    }
                    System.out.println();
                }

            } catch (IOException ex) {
                Logger.getLogger(BlcokTradeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            return null;
        }
    }
}
