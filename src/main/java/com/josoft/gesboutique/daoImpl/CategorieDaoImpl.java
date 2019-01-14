/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.daoImpl;

import com.joel.webapp.core.daoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;
import com.josoft.gesboutique.entities.Categorie;
import com.josoft.gestboutique.dao.ICategorieDao;

/**
 *
 * @author joelkdb
 */
@Stateless
public class CategorieDaoImpl extends BaseDaoBeanImpl<Categorie, Integer> implements ICategorieDao{
    public CategorieDaoImpl(){
        super(Categorie.class);
    }
}
