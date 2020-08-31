/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import DAO.PeliculaDAO;
import java.util.ArrayList;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import model.Pelicula;

/**
 *
 * @author A10
 */
@Path("peliculas")
public class PeliculasResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        PeliculaDAO peliculaDao = new PeliculaDAO();
        ArrayList<Pelicula> peliculas
                = peliculaDao.findAll(null);
        return Pelicula.toArrayJSon(peliculas);
    }
    
}
