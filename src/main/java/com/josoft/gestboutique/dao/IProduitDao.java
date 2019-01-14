/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gestboutique.dao;

import com.joel.webapp.core.dao.BaseDaoBean;
import javax.ejb.Local;
import com.josoft.gesboutique.entities.Produit;

/**
 *
 * @author joelkdb
 */
@Local
public interface IProduitDao extends BaseDaoBean<Produit, Long>{
    
}
