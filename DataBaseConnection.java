/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;


import java.sql.Connection;
import java.sql.DriverManager;

// (this class to connect to database)
public class DataBaseConnection {
     private final String user="root";
     private final String password ="";
     private final String add="jdbc:mysql://localhost:3306/electricitybillingsystem"; 
     private  Connection c;
             public Connection connect ()
             {
                  try {  Class.forName("com.mysql.cj.jdbc.Driver");

        c=DriverManager.getConnection(add,user,password);
        System.out.println("connected");
        }
        catch (Exception e)
        {
            System.out.println("not working");
        }
                  return c;
             }

    }


