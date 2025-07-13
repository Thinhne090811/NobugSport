/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Until;


import Entity.UserE;


/**
 *
 * @author HAPPY
 */
public class XAuth {
public static UserE user = UserE.builder()
.Username("admin")
.Password("123456")
.StatusUser(true)
.Service(true)
.FullnameUser("Nguyễn Văn Tèo")
.PhotoUser("trump.png")
.build();
}
