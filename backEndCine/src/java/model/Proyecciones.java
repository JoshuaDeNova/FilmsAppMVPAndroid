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
public class Proyecciones {
    
    private int idProyeccion, pelicula, sesion;
    private String fecha, horario;

    public Proyecciones() {
    }

    public Proyecciones(int idProyeccion, int pelicula, int sesion, String fecha, String horario) {
        this.idProyeccion = idProyeccion;
        this.pelicula = pelicula;
        this.sesion = sesion;
        this.fecha = fecha;
        this.horario = horario;
    }

    public int getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public int getPelicula() {
        return pelicula;
    }

    public void setPelicula(int pelicula) {
        this.pelicula = pelicula;
    }

    public int getSesion() {
        return sesion;
    }

    public void setSesion(int sesion) {
        this.sesion = sesion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Proyecciones{" + "idProyeccion=" + idProyeccion + ", pelicula=" + pelicula + ", sesion=" + sesion + ", fecha=" + fecha + ", horario=" + horario + '}';
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
