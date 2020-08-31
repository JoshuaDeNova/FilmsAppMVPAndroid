package com.example.cinesjox.Usuarios;

import com.example.cinesjox.models.Usuario;

public class UsuarioPresenter implements UsuarioContract.Presenter{

    private UserContract.View userView;
    private UserModel userModel;

    @Override
    public void validarLogin(Usuario usuario) {

    }

    @Override
    public void failureLogin(String message) {

    }
}
