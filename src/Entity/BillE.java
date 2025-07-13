/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

/**
 *
 * @author HAPPY
 */
public class BillE {
    private int IdBill;
    private String Username;
    private int CustomerId;
    private Date CheckIn;
    private Date CheckOut;
    private boolean Status;

  

    
}
