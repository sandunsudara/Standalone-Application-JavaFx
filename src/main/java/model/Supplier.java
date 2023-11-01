package model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String email;
    private String company;
    private String contact;

}
