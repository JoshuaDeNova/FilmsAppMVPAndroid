package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Pelicula {

    private String titulo, descripcion, trailer, imagen, genero;
    private int numVotos, idPelicula;

    public Pelicula() {
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", descripcion=" + descripcion + ", trailer=" + trailer + ", imagen=" + imagen + ", genero=" + genero + ", numVotos=" + numVotos + ", idPelicula=" + idPelicula + '}';
    }

    public Pelicula(String titulo, String descripcion, String trailer, String imagen, String genero, int numVotos, int idPelicula) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.trailer = trailer;
        this.imagen = imagen;
        this.genero = genero;
        this.numVotos = numVotos;
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public static String
            toArrayJSon(ArrayList<Pelicula> peliculas) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(peliculas);

        return resp;
    }

    public static String toObjectJson(Pelicula pelicula) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(pelicula);
        return resp;
    }
}
