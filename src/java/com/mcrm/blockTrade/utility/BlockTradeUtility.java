/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.blockTrade.utility;

import java.util.Calendar;

/**
 *
 * @author Chris.Yu
 */
public class BlockTradeUtility {

    private static String convertOneDigitToTwoDigit(int number) {
        if (number >= 10) {
            return String.valueOf(number);
        } else {
            return "0" + String.valueOf(number);
        }
    }

    public static String getCurrentDateString() {
        Calendar calendar = Calendar.getInstance();
        String currentCal = convertOneDigitToTwoDigit(calendar.get(Calendar.MONTH)+1) + convertOneDigitToTwoDigit(calendar.get(Calendar.DAY_OF_MONTH)) + convertOneDigitToTwoDigit(calendar.get(Calendar.YEAR));
        return currentCal;
    }

    public static String getRandomThirteenDigitString() {
          return String.valueOf(randomWithBounds(1000000, 9999999))+String.valueOf(randomWithBounds(100000, 999999));
    }
    /*
     return the 
    */
      
    private static int randomWithBounds(int lowerBound, int upperBound){
        return lowerBound + (int)(Math.random()*((upperBound-lowerBound)+1));
    }
}
