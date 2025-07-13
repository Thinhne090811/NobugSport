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
public class CategoryE {
    private String IdCategory;
    private String NameCategory;
@Override
    public String toString() {
        return this.NameCategory;
    }
   
    
}
