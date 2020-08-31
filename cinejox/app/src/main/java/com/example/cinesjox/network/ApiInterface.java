package com.example.cinesjox.network;

import com.example.cinesjox.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("usuarios/login")
    Call<Usuario> userLogin(@Body Usuario user);

}
