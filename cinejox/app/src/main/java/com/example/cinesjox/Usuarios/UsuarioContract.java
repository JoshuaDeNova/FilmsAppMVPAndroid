package com.example.cinesjox.Usuarios;

import com.example.cinesjox.models.Usuario;

public interface UsuarioContract {

    interface Model{
        /*Programación Reactiva*/
        interface OnLoginListener{
            void onFinished(Usuario usuario);
            void onFailure(Throwable t);
        }
        void getUserLogin(
                OnLoginListener OnLoginListener,
                Usuario usuario);
    }

    interface View{
        void sucessLogin(Usuario usuario);
        void failureLogin(String message);
    }

    interface Presenter{
        void validarLogin(Usuario usuario);
        void failureLogin(String message);
    }

}
