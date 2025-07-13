/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

/**
 *
 * @author HAPPY
 */
public class BillDetailsE {
    private int IdBillDetail;
    private int BillId;
    private String ProductId;
    private int UnitPrice;
    private int Quantity;

 
    
}
