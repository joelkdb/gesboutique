/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.service;

import com.joel.webapp.core.service.BaseServiceBean;
import javax.ejb.Local;
import com.josoft.gesboutique.entities.Produit;

/**
 *
 * @author joelkdb
 */
@Local
public interface ProduitSessionBean extends BaseServiceBean<Produit, Long>{
    
}
