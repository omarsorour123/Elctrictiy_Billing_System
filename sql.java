/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.sql.Connection;
import java.util.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author omar
 */
public class sql {
    private final String user="root";
    private final String pass="";
    private final String add="jdbc:mysql://localhost/project";  
    public Connection connect()throws SQLException {
       
       return DriverManager.getConnection(add, user, pass);
     
    }
}
