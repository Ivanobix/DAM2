package com.fvaldeon.gestionalumnos.base;

import java.io.Serializable;

public class Usuario implements Serializable {
    public static final int USUARIO_ADMIN = 0;
    public static final int USUARIO_STANDARD = 1;

    private String login;
    private String password;
    private int rol;

    public Usuario(String login, String password, int rol) {
        this.login = login;
        this.password = password;
        this.rol = rol;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        String textoRol = rol == USUARIO_ADMIN ? "Admin" : "Standard" ;
        return login + ' ' + textoRol;
    }
}
