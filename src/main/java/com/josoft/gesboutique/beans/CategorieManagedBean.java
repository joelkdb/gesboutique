/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.beans;

import com.joel.webapp.core.transaction.TransactionManager;
import com.joel.webapp.utils.Mtm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.josoft.gesboutique.entities.Categorie;
import com.josoft.gesboutique.service.CategorieSessionBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@SessionScoped
public class CategorieManagedBean implements Serializable{
    private Categorie categorie, categorie2, categorie3;
    private Integer idCat;
    
    private List<Categorie> categorieListe;
    
    @EJB
    private CategorieSessionBean categorieServices;

    public CategorieManagedBean() {
        categorie = new Categorie();
        categorieListe = new ArrayList<>();
        categorie2 = new Categorie();
        categorie3 = new Categorie();
    }
    
    public List<Categorie> getAllCategories(){
        categorieListe = categorieServices.getAll();
        return categorieListe;
    }
    
    public void renvoieCat(Categorie ct){
        categorie2 = new Categorie();
        this.categorie2 = ct;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgEdit').show();");
    }
    
    public void miseAjourCategorie(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            categorie2.setLibelle(getCategorie2().getLibelle());
            this.categorieServices.updateOne(categorie2);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération","Catégorie éditer avec succès"));
        }
        categorie2 = new Categorie(); 
    }
    
    public void annulerMiseAjourCategorie(){
        categorie2 = new Categorie();
    }
    public void annulerEnregCat(){
        categorie = new Categorie();
    }
    public void annulerSupCat(){
        categorie3  = new Categorie();
    }
    
    public void renvoieCatSup(Categorie cat){
        categorie3  = new Categorie();
        categorie3 = cat;
    }
    
    public void messageExportPDF(){
        Mtm.joelMessageInfoPerso("Données exportées en PDF sous le nom de fichier 'Categorie.pdf'\nVérifier vos téléchargements");
    }

    public void messageExportXLS(){
        Mtm.joelMessageInfoPerso("Données exportées en fichier Excel sous le nom de fichier 'Categorie.xls'\nVérifier vos téléchargements");
    }
    
    public void supprimerCat(){
        UserTransaction tx = TransactionManager.getUserTransaction();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        boolean supprimer = false;
        try {
            tx.begin();
            supprimer = this.categorieServices.deleteOne(categorie3.getId());
            tx.commit();
            categorie3  = new Categorie();
            if(supprimer==true){
                Mtm.joelMessageInfoPerso("Catégorie retirer avec succès!");
            }else{
                Mtm.joelMessageInfoPerso("Catégorie non retirer, réessayer!");
            }
                
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            Mtm.joelMessageErrorPerso("Erreur dans le processus de retrait");
            categorie3  = new Categorie();
        }
    }
    
    public void enregistrerCat() throws NotSupportedException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            if(categorie.getLibelle().isEmpty()){
                Mtm.joelMessageWarn("Renseigner le libellé");
            }else{
                if(!trouver()){
                    tx.begin();
                    this.categorieServices.saveOne(categorie);
                    tx.commit();
                    categorie = new Categorie();
                    Mtm.joelMessageInfoPerso("Catégorie ajouté avec succès!");
                }else{
                    Mtm.joelMessageErrorPerso("Cette catégorie existe déjà!");
                    categorie = new Categorie();
                }                
            }
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
                Mtm.joelMessageErrorPerso("Problème détecté!");
        }
    }

    public boolean trouver(){
        for (Categorie categorie1 : categorieListe) {
            if(categorie.getLibelle().trim().toLowerCase().equals(categorie1.getLibelle().toLowerCase())){
                return true;
            }
        }
        return false;
    }
    
    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Categorie getCategorie2() {
        return categorie2;
    }

    public void setCategorie2(Categorie categorie2) {
        this.categorie2 = categorie2;
    }

    public List<Categorie> getCategorieListe() {
        return categorieListe;
    }

    public void setCategorieListe(List<Categorie> categorieListe) {
        this.categorieListe = categorieListe;
    }

    public Categorie getCategorie3() {
        return categorie3;
    }

    public void setCategorie3(Categorie categorie3) {
        this.categorie3 = categorie3;
    }

    public CategorieSessionBean getCategorieServices() {
        return categorieServices;
    }

    public void setCategorieServices(CategorieSessionBean categorieServices) {
        this.categorieServices = categorieServices;
    }
    
    
}
