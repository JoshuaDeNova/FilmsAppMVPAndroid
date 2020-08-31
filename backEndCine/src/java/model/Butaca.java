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
public class Butaca {
    
    private int idButaca;
    private String estado;

    public Butaca() {
    }

    public Butaca(int idButaca, String estado) {
        this.idButaca = idButaca;
        this.estado = estado;
    }

    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idButaca) {
        this.idButaca = idButaca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
