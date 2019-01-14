/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "produits")
@XmlRootElement
public class Produit implements Serializable{
    @Id
    @SequenceGenerator(name = "produitSeq", sequenceName = "PRODUIT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "produitSeq")
    private Long id;
    
    @Column(name = "libelle", nullable = false)
    private String libelle;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "datePeremption", nullable = false)
    private Date datePeremption;
    
    @Column(name = "prixUnitaire", nullable = false)
    private Double prixUnitaire;
    
    @Column(name ="prix_unitaire_autre")
    private Float prixUnit;
    
    @ManyToOne(cascade = {})
    @JoinColumn(referencedColumnName = "id",name = "categuorie_FK", nullable = false)
    private Categorie categorie_FK;
   
    public Produit() {
    }

    public Produit(String libelle, Date datePeremption, Double prixUnitaire, Categorie categorie_FK) {
        this.libelle = libelle;
        this.datePeremption = datePeremption;
        this.prixUnitaire = prixUnitaire;
        this.categorie_FK = categorie_FK;
    }
    
    /**
     * Méthode pour vérifier si le produit est périmé
     * @return
     */
    public boolean estPerime(){
        Date dateActuelle = new Date();
        if(this.datePeremption.compareTo(dateActuelle) < 0){
            return true;
        }else{
            return false;
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Categorie getCategorie_FK() {
        return categorie_FK;
    }

    public void setCategorie_FK(Categorie categorie_FK) {
        this.categorie_FK = categorie_FK;
    }

    public Float getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(Float prixUnit) {
        this.prixUnit = prixUnit;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", libelle=" + libelle + ", datePeremption=" + datePeremption + ", prixUnitaire=" + prixUnitaire + ", categorie_FK=" + categorie_FK + '}';
    }
    
    
}
