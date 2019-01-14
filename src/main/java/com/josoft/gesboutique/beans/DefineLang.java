/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author joelkdb
 */
@ManagedBean(name = "defineLang")
@SessionScoped
public class DefineLang implements Serializable{
    //la locale des pages
    private String locale = "fr";
    
    public DefineLang(){
        
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    public String setFrenchLocale(){
        this.locale = "fr";
        return null;
    }
    
    public String setEnglishLocale(){
        this.locale = "en";
        return null;
    }
    
}
