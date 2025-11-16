/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.lap7;


/**
 *
 * @author Zaid
 */
import java.security.MessageDigest;
import java.nio.file.*;
public class User {
    abstract class user{
protected String userId;
protected String role;
protected String username;
protected String email;
protected String passwordHash;
    }
public User(String userId, String role, String username, String email, String passwordHash) {
this.userId = userId;
this.role = role;
this.username = username;
this.email = email;
this.passwordHash = passwordHash;
}
public Stirng userId(){return userId;}
public String Role(){return role;}
public String userName(){return username;}
public String Email(){return email;}
}
