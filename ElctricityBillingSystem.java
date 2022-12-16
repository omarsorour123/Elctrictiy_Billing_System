/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.elctricitybillingsystem;

import java.math.BigInteger;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author omars
 */
public class ElctricityBillingSystem {
static  Scanner input=new Scanner (System.in);
public static void main(String[] args) throws Exception  {
   

//if(pass1.equals(pass2))
        //      System.out.println("same");
       //System.out.println("inserted");
    /*      Customer customer= new Customer() ;         
      LogInCustomer test = new LogInCustomer ();
    if( test.validateAccount(email, pass1,customer))
    {
        System.out.println("correct account");
    }
     
      System.out.println(customer.toString());
*/
      

  String s= "304032521030933";
       if(Pattern.matches("\\d{14}",s))
           System.out.println("strong");
        else
           System.out.println("weak");
        } 

}



/* select password\n" +
"from customer_contact\n" +
"where email= '"+email+"'" */



