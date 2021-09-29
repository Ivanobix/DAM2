package coffeetime.base;

import java.io.Serializable;

/**
 * Usuario. Clase dedicada a la construcción de objetos tipo "Usuario" para su
 * posterior almacenamiento o tratamiento por diferentes clases.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class Usuario implements Serializable {
    public static final int ADMIN = 0;
    public static final int DEFAULT = 1;
    public static final int BASICO = 2;
    private static final long serialVersionUID = 6529685098267757690L;
    private String usuario;
    private String contrasena;
    private int tipoUsuario;

    /**
     * Constructor.
     *
     * @param usuario     Nombre de usuario.
     * @param contrasena  Contraseña del usuario.
     * @param tipoUsuario Nivel de privilegios del usuario.
     */
    public Usuario(String usuario, String contrasena, int tipoUsuario) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Devuelve el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario Nombre del usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena Contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Devuelve nivel de privilegios del usuario.
     *
     * @return Nivel de privilegios del usuario.
     */
    public int getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el nivel de privilegios del usuario.
     *
     * @param tipoUsuario Nivel de privilegios del usuario.
     */
    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Devuelve una cadena con la información básica de un usuario.
     */
    @Override
    public String toString() {
        return usuario;
    }
}
