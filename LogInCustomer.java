/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.elctricitybillingsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Date;
public class LogInCustomer {
    private String email;
    private String password;
    
    
        
    // (to check the email)
    public boolean validateAccount (String email,String password,Customer customer)
    {    DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
        ResultSet r;
         Statement ss ;
         try {
        ss=c.createStatement();
        String query;
        query = " select *\n" +
" from customer_info\n" +
" inner join customer_contact\n" +
" on customer_contact.metercode= customer_info.meterCode\n" +
" inner join bill_info \n" +
" on customer_info.meterCode=bill_info.meterCode\n" +
" where email='"+email+"'";
        r=ss.executeQuery(query);
       if(!r.next())
       {
           System.out.println("enter valid email");
           return false;
       }
       else 
       {  String temp ;
          temp = r.getString("password");
         if (validatePassword (password,temp))
         {   setCustomerInfo (customer,r);
             return true;
       }
         else 
         {
             System.out.println("enter valid password");
             return false;
         }
         }
         }
         catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
        return false;
    }
    // (to check the password)
    private boolean validatePassword (String password,String temp)
    {
        return (password.equals(temp));
    }
    private void setCustomerInfo (Customer customer, ResultSet r)
    {  try {
     Customer temp = new Customer ( r.getInt("meterCode"),r.getString("firstname"),r.getString("lastname"),r.getInt("region"),r.getString("email"), r.getString("password"),r.getString("phonenum"),r.getInt("triff"),r.getInt("consumption"),r.getString("ssn"),r.getDate("payingdate"));
     customer.copyData (temp) ;   
  
                System.out.println("done");
    }
    catch(SQLException e) {
        System.out.println(e.getMessage());;
    }
   
    }
}
