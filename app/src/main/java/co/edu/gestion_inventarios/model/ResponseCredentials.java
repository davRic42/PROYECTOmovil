package co.edu.gestion_inventarios.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseCredentials implements Serializable {
    private ArrayList<Credentials> credenciales;
    private String mensaje;
    public ResponseCredentials(){

    }

    public ArrayList<Credentials> getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(ArrayList<Credentials> credenciales) {
        this.credenciales = credenciales;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
