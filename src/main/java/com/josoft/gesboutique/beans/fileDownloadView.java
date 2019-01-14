/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class fileDownloadView implements Serializable {

    private StreamedContent download;
    private String test = "Télécharger";
    private String test2 = "names.txt";

    public fileDownloadView() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String path = externalContext.getRealPath("/");
        System.out.println(path);
    }

    public StreamedContent createFileStream(String name) {
        InputStream stream = null;
        try {
            if (!name.isEmpty() || name == null) {
                stream = new FileInputStream(
                        FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resouces/file")
                        + File.separator
                        + name);
            }
        } catch (Exception ex) {
            Logger.getLogger(fileDownloadView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DefaultStreamedContent(stream, null, name);
    }

    public StreamedContent getDownload() {
        return download;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }
    
    

}
