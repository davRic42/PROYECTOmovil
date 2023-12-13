package co.edu.gestion_inventarios.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Categoria implements Serializable {
    public String name_category;
    public String id_category;

    public Categoria(String name_category, String id_category) {
        this.name_category = name_category;
        this.id_category = id_category;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "name_category='" + name_category + '\'' +
                ", id_category='" + id_category + '\'' +
                '}';
    }
}
