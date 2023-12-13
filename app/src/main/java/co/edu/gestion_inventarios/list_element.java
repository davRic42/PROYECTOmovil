package co.edu.gestion_inventarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class list_element implements Serializable {
    public String nameCard;
    public String numberCard;

    public list_element(String color, String nameCard, String numberCard) {
        this.nameCard = nameCard;
        this.numberCard = numberCard;
    }
    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }
}
