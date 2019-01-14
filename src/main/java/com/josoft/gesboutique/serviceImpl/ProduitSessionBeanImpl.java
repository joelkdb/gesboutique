/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.serviceImpl;

import com.joel.webapp.core.dao.BaseDaoBean;
import com.joel.webapp.core.serviceImpl.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.josoft.gesboutique.entities.Produit;
import com.josoft.gesboutique.service.ProduitSessionBean;
import com.josoft.gestboutique.dao.IProduitDao;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ProduitSessionBeanImpl extends BaseServiceBeanImpl<Produit, Long> implements ProduitSessionBean{
   
    @EJB
    private IProduitDao dao;
    
    @Override
    protected BaseDaoBean<Produit, Long> getDao(){
        return dao;
    }
}
