package co.edu.gestion_inventarios.model;

import java.io.Serializable;

public class Loger implements Serializable {
    private String user_pss;
    private String user_mail;
    private String user_name;
    private String user_id;
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pss() {
        return user_pss;
    }

    public void setUser_pss(String user_pss) {
        this.user_pss = user_pss;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    @Override
    public String toString() {
        return "Loger{" +
                "user_pss='" + user_pss + '\'' +
                ", user_mail='" + user_mail + '\'' +
                '}';
    }
}
