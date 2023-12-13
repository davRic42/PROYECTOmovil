package co.edu.gestion_inventarios.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseCredentials implements Serializable {
    private ArrayList<Credentials> credenciales;
    private String mensaje;
    private String user_name;
    private String user_id;
    public ResponseCredentials(){

    }

    public ArrayList<Credentials> getCredenciales() {
        return credenciales;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
