/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.daoImpl;

import com.joel.webapp.core.daoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;
import com.josoft.gesboutique.entities.Produit;
import com.josoft.gestboutique.dao.IProduitDao;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ProduitDaoImpl extends BaseDaoBeanImpl<Produit, Long> implements IProduitDao{
    public ProduitDaoImpl(){
        super(Produit.class);
    }
}
