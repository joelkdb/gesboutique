package com.josoft.gesboutique.entities;

import com.josoft.gesboutique.entities.Categorie;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-31T10:09:58")
@StaticMetamodel(Produit.class)
public class Produit_ { 

    public static volatile SingularAttribute<Produit, Categorie> categorie_FK;
    public static volatile SingularAttribute<Produit, Double> prixUnitaire;
    public static volatile SingularAttribute<Produit, String> libelle;
    public static volatile SingularAttribute<Produit, Long> id;
    public static volatile SingularAttribute<Produit, Date> datePeremption;
    public static volatile SingularAttribute<Produit, Float> prixUnit;

}