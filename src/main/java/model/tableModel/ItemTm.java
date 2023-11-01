package model.tableModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String itemId;
    private String description;
    private double cost;
    private double salesPrice;
    private int qty;
    private String size;
    private String type;
    private String supplierId;
    private JFXButton btn;
}
