/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Joshua
 */
public class Historico {

    private int pelicula, usuario, idHistorico;

    public Historico() {
    }

    public Historico(int idHistorico, int pelicula, int usuario) {
        this.pelicula = pelicula;
        this.usuario = usuario;
        this.idHistorico = idHistorico;
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public int getPelicula() {
        return pelicula;
    }

    public void setPelicula(int pelicula) {
        this.pelicula = pelicula;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public static String toObjectJson(Pelicula pelicula) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(pelicula);
        return resp;
    }

}
