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
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.josoft.gesboutique.entities.Produit;
import com.josoft.gesboutique.service.CategorieSessionBean;
import com.josoft.gesboutique.service.ProduitSessionBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@SessionScoped
public class EditProdManagedBean implements Serializable {

    private Date date;
    private Produit produit, produit2;
    private Integer idCateg;
    private List<Produit> listeProduit;
    private List<Produit> listeProduit2;

    @EJB
    private CategorieSessionBean categorieService;
    
    @EJB
    private ProduitSessionBean produitServices;

    public EditProdManagedBean() {
        produit = new Produit();
        produit2  = new Produit();
        listeProduit = new ArrayList<>();
        listeProduit2  = new ArrayList<>();
    }

    public List<Produit> getAllProduit(){
        if(idCateg == null){
            return null;
        }else{
            listeProduit = produitServices.getBy("categorie_FK", categorieService.getOne(idCateg));
            return listeProduit;
        }    
    }
    
    public List<Produit> getAllProduit2(){
        listeProduit2 = produitServices.getAll("id", false);
        return listeProduit2;
    }
    
    public void renvoieProdSup(Produit prod){
        //produit2 = new Produit();
        produit2 = prod;
    }
    
    public void annulerSupProd(){
        produit2 = new Produit();
    }
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Date dateActuelle = new Date();
        if (produit.getDatePeremption().compareTo(dateActuelle) < 0) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date péremption: ", "Le produit est périmé"));
        }
    }

    public void enregistrerProd() {
        try {
            if (produit.getLibelle().isEmpty()) {
                Mtm.joelMessageWarn("Renseigner le libellé");
            } else if (produit.getPrixUnitaire() == 0) {
                Mtm.joelMessageWarn("Renseigner le prix unitaire");
            } else if (produit.getDatePeremption() == null) {
                Mtm.joelMessageWarn("Choisir la date de péremption");
            } else if (idCateg == null) {
                Mtm.joelMessageWarn("Choisir la categorie du produit");
            } else {
                if (produit.getId() == null) {
                    produit.setCategorie_FK(categorieService.getOne(idCateg));
                    produitServices.saveOne(produit);
                    Mtm.joelMessageInfoPerso("Produit ajouté avec succès");
                    produit = new Produit();
                    idCateg = null;
                } else {
                    produit.setCategorie_FK(categorieService.getOne(idCateg));
                    this.produitServices.updateOne(produit);
                    Mtm.joelMessageInfoPerso("Produit mis à jour avec succès");
                    produit = new Produit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Mtm.joelMessageErrorPerso("Probleme detecté !");
        }
    }
    
    public void messageExportPDF(){
        Mtm.joelMessageInfoPerso("Données exportées en PDF sous le nom de fichier 'ProduitDeCategorie.pdf'\nVérifier vos téléchargements");
    }

    public void messageExportXLS(){
        Mtm.joelMessageInfoPerso("Données exportées en fichier Excel sous le nom de fichier 'ProduitDeCategorie.xls'\nVérifier vos téléchargements");
    }
    
    public void supprimerProduit(){
        UserTransaction tx = TransactionManager.getUserTransaction();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        boolean supprimer = false;
        try {
            tx.begin();
            System.out.println("Id : "+produit2.getId()+ "; Libellé : " + produit2.getLibelle());
            supprimer = this.produitServices.deleteOne(produit2.getId());
            tx.commit();
            produit2  = new Produit();
            if(supprimer==true){
                Mtm.joelMessageInfoPerso("Produit retirer avec succès!");
            }else{
                Mtm.joelMessageWarn("Produit non retirer, réessayer!");
                produit2  = new Produit();
            }
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
          //} catch(Exception e){
            e.printStackTrace();
            Mtm.joelMessageErrorPerso("Erreur dans le processus de retrait");
            produit2  = new Produit();
        }
    }
    
    public Date renvoieDateActu(){
        return new Date();
    }
    
    public String renvoieProduit(Produit prod) {
        produit = new Produit();
        try {
            idCateg = prod.getCategorie_FK().getId();
            produit = prod;
            return "EditProduit?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }        
    }

    public void annulerProduit() {
        produit = new Produit();
        idCateg = null;
    }
    
    public void annulerBean(){
        produit = new Produit();
        produit2 = new Produit();
        idCateg = null;
    }
    
    public String  nouveauProd(){
        annulerBean();
        return "EditProduit?faces-redirect=true";
    }
    
    //Méthode de validation du prix unitaire
    public void validerPU(FacesContext contexte, UIComponent composant, Object value){
        Pattern p = Pattern.compile("^[0-9]{2,8}$");
        Matcher m = p.matcher((String) value);
        if(!m.matches()){
            throw new ValidatorException(new FacesMessage( 
					FacesMessage.SEVERITY_WARN, "Validation prix unitaire :", 
					"Valeur du prix unitaire est erronée"));
        }
    }

    public Date getDate() {
        return date;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public ProduitSessionBean getProduitServices() {
        return produitServices;
    }

    public void setProduitServices(ProduitSessionBean produitServices) {
        this.produitServices = produitServices;
    }

    public Integer getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(Integer idCateg) {
        this.idCateg = idCateg;
    }

    public CategorieSessionBean getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(CategorieSessionBean categorieService) {
        this.categorieService = categorieService;
    }

    public List<Produit> getListeProduit() {
        return listeProduit;
    }

    public void setListeProduit(List<Produit> listeProduit) {
        this.listeProduit = listeProduit;
    }

    public Produit getProduit2() {
        return produit2;
    }

    public void setProduit2(Produit produit2) {
        this.produit2 = produit2;
    }    

    public List<Produit> getListeProduit2() {
        return listeProduit2;
    }

    public void setListeProduit2(List<Produit> listeProduit2) {
        this.listeProduit2 = listeProduit2;
    }

}
