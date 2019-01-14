/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josoft.gesboutique.rest.service;

import com.josoft.gesboutique.entities.Categorie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joelkdb
 */
@Stateless
@Path("/CategorieService")
public class CategorieFacadeREST extends AbstractFacade<Categorie> {

    @PersistenceContext(unitName = "com.softnetwork_GESBOUTIQUE_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CategorieFacadeREST() {
        super(Categorie.class);
    }

    @POST
    @Override
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Categorie entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Categorie entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Categorie find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("/categories")
    @Override
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Categorie> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Categorie> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
