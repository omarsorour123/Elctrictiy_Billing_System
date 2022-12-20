/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author omars
 */
public class LogInOperator {
      public boolean validateAccount (String email,String password,Opertaor op)
      { DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
        ResultSet r;
         Statement ss ;
         try {
        ss=c.createStatement();
        String query;
        query = "SELECT *\n" +
"from operator_info\n" +
"where email ='"+email+"'";
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
         {   setOperatorInfo (op,r);
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
        private boolean validatePassword (String password,String temp)
    {
        return (password.equals(temp));
    }
        private void setOperatorInfo (Opertaor op, ResultSet r) throws SQLException
        {
      op.setId(r.getInt("id"));
      op.setFname(r.getString("fname"));
      op.setLname(r.getString("lname"));
      op.setEmail(r.getString("email"));
      op.setPass(r.getString("password"));
      op.setSsn(r.getString("ssn"));

        }
}
