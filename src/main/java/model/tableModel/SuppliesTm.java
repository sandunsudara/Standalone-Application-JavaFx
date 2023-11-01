package model.tableModel;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuppliesTm extends RecursiveTreeObject<SuppliesTm> {
    private String itemId;
    private String description;
    private int qty;
}
