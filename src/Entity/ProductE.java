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
public class ProductE {
    private String IdProduct;
    private String NameProduct;
    @Builder.Default
    private String ImageProduct = "product.png";
    private double PriceProduct;
    private double DiscountProduct;
    private boolean StatusProduct;
    private String CategoryId;
}
