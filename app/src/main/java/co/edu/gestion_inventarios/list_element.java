package co.edu.gestion_inventarios;

import java.io.Serializable;

public class list_element implements Serializable {
    public String color;
    public String nameCard;
    public String numberCard;

    public list_element(String color, String nameCard, String numberCard) {
        this.color = color;
        this.nameCard = nameCard;
        this.numberCard = numberCard;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
