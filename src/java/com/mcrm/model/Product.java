/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Chris.Yu
 */
public class Product {

    private Calendar calendar;
    private String monthCode;
    private String year;
    private double strike;
    private OptionType optiontype = OptionType.UNKNOWN;
    private double price;
    private int quantity;
    private String clearingCode;
    private String productCode;
    private ContractType type = ContractType.UNKNOWN;
    private static final Map<String, String> monthCodeMap;
    private static final Map<String, String> productTypeMap;
    /*
     productCode--ate int quantity;
    private String clearingCode;
    private String productCode;
    private ContractType ty
     option:
     LNJ4|4500P
     future:
     NGG6
     1LH4|05#
     */
    private static final String REGEX_OPTION = "^([A-Z]+)([A-Z]{1})(\\d{1})(\\|)(\\d+)(P|C)";
    private static final String REGEX_FUTURE = "^([A-Z]+)([A-Z]{1})(\\d{1})";

    static {
        monthCodeMap = new HashMap<>(12);
        monthCodeMap.put("F", "Jan");
        monthCodeMap.put("G", "Feb");
        monthCodeMap.put("H", "Mar");
        monthCodeMap.put("J", "Apr");
        monthCodeMap.put("K", "May");
        monthCodeMap.put("M", "Jun");
        monthCodeMap.put("N", "Jul");
        monthCodeMap.put("Q", "Aug");
        monthCodeMap.put("U", "Sep");
        monthCodeMap.put("V", "Oct");
        monthCodeMap.put("X", "Nov");
        monthCodeMap.put("Z", "Dec");
        productTypeMap = new HashMap<>(2);
        productTypeMap.put("C", "Call");
        productTypeMap.put("P", "Put");
    }

    public Product(Calendar calendar, double price, int quantity, String productCode) {
        this.calendar = calendar;
        this.price = price;
        this.quantity = quantity;
        this.productCode = productCode;
    }
   
    
    private void initializeFields(String productCode) {
        //this is an option
        this.productCode=productCode;
        if (productCode.contains("|") && !productCode.contains("#")) {
          populateFields4Option(productCode);
        } //this is an future
        else {
          populateFields4Future(productCode); 
        }
    }

    public String getTime() {
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }

    public String getMonth() {
        return monthCodeMap.get(monthCode) + "-" + year;
    }

    public enum ContractType {

        FUTURE, OPTION, SPREAD, UNKNOWN;
    }

    public enum OptionType {

        CALL, PUT, UNKNOWN;
    }
    /*
     private static final String REGEX_OPTION = "^([A-Z]+)([A-Z]{1})(\\d{1})(\\|)(\\d+)(P|C)";
     private static final String REGEX_FUTURE = "^([A-Z]+)([A-Z]{1})(\\d{1})";
     */

    private void populateFields4Option(String productCode) {
        Pattern pattern = Pattern.compile(REGEX_OPTION);
        Matcher matcher = pattern.matcher(productCode);
        this.clearingCode = matcher.group(1);
        this.monthCode = matcher.group(2);
        this.year = converYearFourDigit(matcher.group(3));
        this.strike = Double.valueOf(matcher.group(5));
        this.optiontype = OptionType.valueOf(matcher.group(6));
    }
    private void populateFields4Future(String productCode) {
        Pattern pattern = Pattern.compile(REGEX_FUTURE);
        Matcher matcher = pattern.matcher(productCode);
        this.clearingCode = matcher.group(1);
        this.monthCode = matcher.group(2);
        this.year = converYearFourDigit(matcher.group(3));
    }

    private String converYearFourDigit(String year) {
        int currentYearLastDigit = Calendar.getInstance().get(Calendar.YEAR);
        if (Double.valueOf(year) < currentYearLastDigit) {
            return "202" + year;
        } else {
            return "201" + year;
        }
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getMonthCode() {
        return monthCode;
    }

    public String getYear() {
        return year;
    }

    public OptionType getOptiontype() {
        return optiontype;
    }

    public double getPrice() {
        return price;
    }

    public double getStrike() {
        return strike;
    }
   
}
