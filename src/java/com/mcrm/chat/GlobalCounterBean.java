/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcrm.chat;

/**
 *
 * @author Chris.Yu
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "counterBean")
@SessionScoped
public class GlobalCounterBean implements Serializable {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public synchronized void increment() {
        count++;
        System.out.println("The count is: "+count);
    }
}

