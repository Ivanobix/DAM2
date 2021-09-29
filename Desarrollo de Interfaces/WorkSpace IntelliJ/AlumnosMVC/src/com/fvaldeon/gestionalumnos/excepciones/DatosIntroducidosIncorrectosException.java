package com.fvaldeon.gestionalumnos.excepciones;

public class DatosIntroducidosIncorrectosException extends Throwable {

    private String localizedMessage;

    public DatosIntroducidosIncorrectosException(String mensaje) {
        super(mensaje);
    }

    //Metodo para indicar un mensaje localizado (internacionalizacion)
    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        if(localizedMessage == null){
            return getMessage();
        }
        return localizedMessage;
    }
}
