/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.beans;

import com.webapp.core.rest.webservices.WSTools;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class FileUploadView {

    private String uploadFileName;
    private String compileFileName;
    private UploadedFile file;

    public FileUploadView() {
    }

    public void handleFileUpload(boolean bool) {
        byte[] data = this.file.getContents();
        System.out.println("Le booléen : " + bool);
        this.uploadFileName = this.file.getFileName();
        System.out.println(this.uploadFileName);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        try {
            String newFileName = "/home/joelkdb/Documents/equipe";
            File dir = new File(newFileName);
            boolean isCreated = dir.mkdirs();
            if(isCreated) {
                FileOutputStream output;
                output = new FileOutputStream(new File(dir+File.separator+this.file.getFileName()));
                output.write(data, 0, data.length);
                output.close();
                System.out.println("Fichier chargé avec succès!!");
                //this.compileFileName = context.getRealPath("/resources/file/" + this.file.getFileName());
                //System.out.println("Url fichier : " + this.compileFileName);
//            :FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
//                    .handleNavigation(FacesContext.getCurrentInstance(), null, "/test/EditProduit.xhtml");
                //FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                System.out.println(WSTools.get("http://localhost:8080/GESBOUTIQUE/rest/CategorieService/count"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'écriture du fichier.");
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getCompileFileName() {
        return compileFileName;
    }

    public void setCompileFileName(String compileFileName) {
        this.compileFileName = compileFileName;
    }

}
