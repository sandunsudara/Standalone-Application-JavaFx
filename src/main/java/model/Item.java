package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Item {

    private String itemId;
    private String description;
    private double cost;
    private double salesPrice;
    private int qty;
    private String size;
    private String type;
    private String supplierId;

}
