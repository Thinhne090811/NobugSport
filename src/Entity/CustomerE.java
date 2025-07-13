/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import lombok.*;

/**
 *
 * @author HAPPY
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerE {
    private String IdCustomer;
    private String Phone;
    private String FullnameCustomer;
    private int TotalTarget;
    private double DiscountCustomer;
    private int Level;
}
