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
public class Cine {
    
    private int idCine;
    private String nombre, direccion, precioEntrada;

    public Cine() {
    }

    public Cine(int idCine, String nombre, String direccion, String precioEntrada) {
        this.idCine = idCine;
        this.nombre = nombre;
        this.direccion = direccion;
        this.precioEntrada = precioEntrada;
    }

    public int getIdCine() {
        return idCine;
    }

    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(String precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    @Override
    public String toString() {
        return "Cine{" + "idCine=" + idCine + ", nombre=" + nombre + ", direccion=" + direccion + ", precioEntrada=" + precioEntrada + '}';
    }
    
    public static String 
        toArrayJSon(ArrayList<Cine> cines) {
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            String resp = gson.toJson(cines);
            
            return resp;
    }
        
    public static String 
        toObjectJSon(Cine cine) {
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            String resp = gson.toJson(cine);
            
            return resp;
    }
    
}
