/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author omars
 */


public class Admin {
    public void deleteCustomer (int meeterCode )
    {  DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
    
         try {
    String query;
      // to delete customer    
    query = "delete from customer_info where meterCode = ?";
             ss=c.prepareStatement(query);
            ss.setInt (1,meeterCode) ;    
            ss.execute();
             System.out.println("the customer has been deleted");
         }
         catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }

        
    }
    
    
    public void addNewCustomer (Customer customer)
    {
        int m = 0;
         DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
         ResultSet r;
         try {
    String query;
    
    //insert in first table
        query = "insert into customer_info "
                + "(firstname,lastname,ssn,region)"
                + "values (?,?,?,?)";
             ss=c.prepareStatement(query);
             ss.setString(1, customer.getF_name());
             ss.setString (2,customer.getL_name());
             ss.setString(3, customer.getSSN());
             ss.setInt (4,1);
            ss.execute();
            
            // to get the meterCode
            query = " select MAX(meterCode) as max_materCode from customer_info";
            ss=c.prepareStatement(query);
            r=ss.executeQuery();
            r.next();
            m = r.getInt (1);
            
            //insert in second table
            query = "insert into customer_contact  (email,password,phonenum,metercode)"
                    + "values (?,?,?,?)";
           ss=c.prepareStatement(query);
           ss.setString(1,customer.getEmail());
           ss.setString(2, customer.getPassword());
           ss.setString (3,customer.getPhoneNumber());
           ss.setInt(4,m);
           ss.execute();
           
           //insert in third table
           query= "insert into bill_info (triff,consumption,payingdate,metercode)"
                   + "values (10,0,?,?) ";
           ss=c.prepareStatement(query);
           ss.setDate(1, customer.getPayingDate());
           ss.setInt(2,m);
           ss.execute();
             System.out.println("customer added");
         }
         // if there any error happend delete any info about this meter code 
         catch (SQLException e)
         {   deleteCustomer(m);
             System.out.println(e.getMessage());
         }
    }
    
    
    public void updateCustomer (int meterCode , String feild,int value)
    {
         DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
         ResultSet r;
         try {
    String query;
    

        query = "update bill_info\n" +
"set "+feild+"  = ? \n" +
"where meterCode = ?";
             ss=c.prepareStatement(query);
             
             ss.setInt(1, value);
             ss.setInt (2,meterCode);
             ss.execute();
         }
         catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
                
    }
    
    
     public void updateCustomer (int meterCode , String feild,java.sql.Date x)
     {
         try {
    String query;
    

        query = "update bill_info\n" +
"set  payingdate = ? \n" +
"where meterCode = ?";
        DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
         ResultSet r;  
        ss=c.prepareStatement(query);
             
            ss.setDate(1, x);
            ss.setInt(2, meterCode);
             ss.execute();
         }
         catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
         
     }
}
