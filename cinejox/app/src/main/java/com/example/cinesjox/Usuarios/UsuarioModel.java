package com.example.cinesjox.Usuarios;

import com.example.cinesjox.models.Usuario;
import com.example.cinesjox.network.ApiClient;
import com.example.cinesjox.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioModel implements UsuarioContract.Model{

    @Override
    public void getUserLogin(OnLoginListener OnLoginListener, Usuario usuario) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<Usuario> call = apiService.userLogin(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(usuario!=null){
                    OnLoginListener.onFinished(usuario);
            }else{
                // Personalización
                OnLoginListener.onFinished(null);
            }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    OnLoginListener.onFailure(null);
                }
        });
    }
}
