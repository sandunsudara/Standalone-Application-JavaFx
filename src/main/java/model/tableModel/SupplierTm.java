package model.tableModel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SupplierTm extends RecursiveTreeObject<SupplierTm> {
    private String supplierId;
    private String supplierName;
    private String email;
    private String company;
    private String contact;
    private JFXButton btn;


}
