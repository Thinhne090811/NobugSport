/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author HAPPY
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserE {
    private String Username;
    private String Password;
    private String FullnameUser;
    private boolean StatusUser;    
    private Date Birthday;
    @Builder.Default
    private String PhotoUser = "photo.png";
    private boolean Service;
}
