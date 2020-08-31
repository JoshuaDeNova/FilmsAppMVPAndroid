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
public class Sesion {
    
    private int idSesion, butaca;
    private String sala, aforo;

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getButaca() {
        return butaca;
    }

    public void setButaca(int butaca) {
        this.butaca = butaca;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getAforo() {
        return aforo;
    }

    public void setAforo(String aforo) {
        this.aforo = aforo;
    }

    @Override
    public String toString() {
        return "Sesion{" + "idSesion=" + idSesion + ", butaca=" + butaca + ", sala=" + sala + ", aforo=" + aforo + '}';
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
