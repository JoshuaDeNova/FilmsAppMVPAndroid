/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author Joshua
 */
public class SesionCinema {
    
    private int cine, sesion;

    public SesionCinema() {
    }

    public SesionCinema(int cine, int sesion) {
        this.cine = cine;
        this.sesion = sesion;
    }

    public int getCine() {
        return cine;
    }

    public void setCine(int cine) {
        this.cine = cine;
    }

    public int getSesion() {
        return sesion;
    }

    public void setSesion(int sesion) {
        this.sesion = sesion;
    }
    
    public static String 
        toArrayJSon(ArrayList<Usuario> usuarios) {
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            String resp = gson.toJson(usuarios);
            
            return resp;
    }
        
    public static String 
        toObjectJSon(Usuario usuario) {
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            String resp = gson.toJson(usuario);
            
            return resp;
    }
    
}
