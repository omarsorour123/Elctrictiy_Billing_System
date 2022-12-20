/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.elctricitybillingsystem;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



/**
 *
 * @author omarsv
 */

public class ElctricityBillingSystem {
static  Scanner input=new Scanner (System.in);
 
void CustomerMeterRead(Customer customer , int consumption)
{
 try {
    String query;
    

        query = "update bill_info\n" +
"set  consumption = ? \n" +
"where meterCode = ?";
        DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
         ResultSet r;  
        ss=c.prepareStatement(query);
             
            ss.setInt(1,consumption);
            ss.setInt(2, customer.getMeterCode());
             ss.execute();
         }
         catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
}
void wow (String ii)
{
    System.out.println("ii");
}
       
   static void checkPayingDate (String email) throws Exception
   {
        DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
         ResultSet r,em;
         try {
    String query;
     
      // to check every person in the system
        query = "select email,payingdate from customer_contact inner join bill_info\n" +
"using (metercode)";
             ss=c.prepareStatement(query);
           //  ss.setInt(1,2022008);
            r= ss.executeQuery();
            
           while (r.next())
              // gett the email of the person
           {   
              String Email = r.getString("email");
              
         //   em= ss.executeQuery();
       //     String Email = em.getString("email");
                java.util.Date date= new java.util.Date (); 
              java.util.Date date2= new java.util.Date (); 
            date=r.getDate("payingdate");
           // check if passed 3 months
            if(date2.getMonth()-date.getMonth()>3)
            {    // send email
                
                JavaMailUtil ssd = new JavaMailUtil();
               ssd.setEmailMessage ("you did not pay for 3 months you have only 1 month or your sub will be canceled");
         ssd.setEmailSubject(Email);
       ssd.sendMail ("omarsorour.os@gmail.com");
            }
         }
         }
          catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
         }
         
         
   
   
         static void updatecons (int value,int meter)
         {
             DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
       String query; 
       try {
           int i = 2022008;
           query = "UPDATE bill_info\n" +
"SET consumption = ?\n" +
"where metercode =?";
           ss=c.prepareStatement(query);
           ss.setInt (1,value);
           ss.setInt (2,meter);
           ss.execute();
           System.out.println("consumption has been updated");
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
       }
         }
         
         
       
public static void main(String[] args) throws Exception   {

Contract co = new Contract ();
co.addContract("C:\\Users\\omars\\Desktop\\BillingSystemUml.jpg", 2022017);
thesignin sign= new thesignin();
sign.setVisible(true);
}
        }





/* select password\n" +
"from customer_contact\n" +
"where email= '"+email+"'" */



