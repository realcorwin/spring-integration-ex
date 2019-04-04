package dik.springintegrationex.model;

import lombok.Data;

@Data
public class Order {

    private int id;
    private String drinkName;
    private int drinkSize;

    public Order(String drinkName, int drinkSize) {
        this.drinkName = drinkName;
        this.drinkSize = drinkSize;
    }
}
