package dik.springintegrationex.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Drink {
    public static final int NUMBER_OF_SIZES = 4;
    private String name;
    private int size;
}
