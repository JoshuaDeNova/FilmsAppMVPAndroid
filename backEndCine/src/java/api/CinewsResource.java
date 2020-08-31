/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import DAO.CineDAO;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.Cine;

/**
 * REST Web Service
 *
 * @author Joshua
 */
@Path("cines")
public class CinewsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CinewsResource
     */
    public CinewsResource() {
    }

    /**
     * Retrieves representation of an instance of api.CinewsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Cine cine = new Cine();
        CineDAO cineDao = new CineDAO();
        ArrayList<Cine> cines
                = cineDao.findAll(null);
        return cine.toArrayJSon(cines);
    }

    /**
     * PUT method for updating or creating an instance of CinewsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
