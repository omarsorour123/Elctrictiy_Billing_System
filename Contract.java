/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author omars
 */
public class Contract {
    private String img;
    private int meter;
    private int status;
   
     public void addContract (String img , int meter) throws FileNotFoundException
     {
         DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
       String query; 
       try {
           query = "INSERT INTO contract \n" +
"VALUES (?,?,?)";
           ss=c.prepareStatement(query);
           InputStream in = new FileInputStream (img) ;
           ss.setBlob(1, in);
           ss.setInt (2,meter);
           ss.setInt (3,0);
           ss.execute();
           System.out.println("contract inserted");
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
       }   
    }
    
}
