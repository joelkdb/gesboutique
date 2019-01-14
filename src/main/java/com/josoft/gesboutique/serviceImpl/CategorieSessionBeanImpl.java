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
import com.josoft.gesboutique.entities.Categorie;
import com.josoft.gesboutique.service.CategorieSessionBean;
import com.josoft.gestboutique.dao.ICategorieDao;

/**
 *
 * @author joelkdb
 */
@Stateless
public class CategorieSessionBeanImpl extends BaseServiceBeanImpl<Categorie, Integer> implements CategorieSessionBean{
   
    @EJB
    private ICategorieDao dao;
    
    @Override
    protected BaseDaoBean<Categorie, Integer> getDao(){
        return dao;
    }
}
