/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;

import java.sql.Statement;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author omar
 */
public class Opertaor {
    static Statement ss;
    static String query;//used in traiff and traiff_print
    static String query2="UPDATE operator_payment SET collected_payment=? WHERE id=?";//collectedPayment
    static String query3;//used in traiff
    static String query4="UPDATE bill_info SET triff=? WHERE meterCode=?";//traiff
    static String query9;//collected payment
    static String query10;//validate
    static String query11;//print_bill
    static String query13="UPDATE bill_info SET consumption=? WHERE meterCode=?";//validate
    static String query14="delete from bill_info where meterCode=?";//cancelsubscribation
    static String query15="delete from customer_info where meterCode=?";//cancelsubscribation
    static String query16="delete from customer_contact where Metercode=?";//cancelsubscribation
    static String query17;//show region bill
    static Connection c;
    static ResultSet r;
    static ResultSet l;
    private String ssn;
    private String fname;
    private int id;
    private String lname;
    private String email;
    private String pass;
    private int total_collected;

    public Opertaor() {
    }
    
    //function to calculate traiff
    public int tariff(int code) throws SQLException {
         int traiff;
         int firstCard=9,secondCard=13,thirdCard=20;
         //Scanner input=new Scanner(System.in);
       // System.out.println("please enter your meter code");
       
        DataBaseConnection c1=new DataBaseConnection();
         c=c1.connect();
         ss=c.createStatement();
         //query to get consumption by metercode
         query="SELECT consumption FROM bill_info WHERE metercode="+code;
         r=ss.executeQuery(query);
       r.next();
       traiff=r.getInt("consumption");
       //here we calculate the traiff
       if(traiff>0&&traiff<50){
           traiff=traiff*firstCard;
       }else if(traiff<=50&&traiff>100){
           traiff=traiff*secondCard;
       }else{
           traiff=traiff*thirdCard;
       }
       //query3 is to update consumption to 0
       query3="UPDATE bill_info SET consumption=0 WHERE meterCode= "+code;
       ss.execute(query3);
       //query4 update triff 
       PreparedStatement k =c.prepareStatement(query4);
         k.setInt(1,traiff);
         k.setInt(2,code);
         k.execute();
         
         return traiff;
    }
//////////////////////////////////////////////////////////////////////////////////////
  
    
    
    
    
    
    
     //this function we use only to show traiff in print_bills it doesonot change any thing 
     public int tariff_print(int code)  {
         int traiff=1, firstCard=9,secondCard=13,thirdCard=20;
        DataBaseConnection c1=new DataBaseConnection();
        c=c1.connect();
        try {
            ss=c.createStatement();
            query="SELECT consumption FROM bill_info WHERE metercode="+code;
         l=ss.executeQuery(query);
       l.next();
       traiff=l.getInt("consumption");
     //    System.out.println(traiff);
        } catch (SQLException ex) {
           // System.out.println("shit");
            System.out.println(ex.getMessage());
        }
         //this query is the same from traiff function
          if(traiff>0&&traiff<50){
           traiff=traiff*firstCard;
       }else if(traiff<=50&&traiff>100){
           traiff=traiff*secondCard;
       }else{
           traiff=traiff*thirdCard;
       }
      
       return traiff;
    }
////////////////////////////////////////////////////////////////////////////////////
     //function collect payment and put it in operator_payment table in database
     public void collectPayment(int meterCode,int id) throws SQLException{
         int tariff;
         //we use function traiff
         tariff = tariff(meterCode);
         
       
         //query9 this query we take the collected payment from the operator to add it to the newpayment
         query9="SELECT collected_payment FROM operator_payment WHERE id="+id;
         r=ss.executeQuery(query9);
         r.next();
         int operatorPayment;
         operatorPayment=r.getInt("collected_payment");
         int totalCollected=tariff+operatorPayment;
         //query2 this query we update the collected_payment=collected_payment+thecost he take from the customer
         PreparedStatement o =c.prepareStatement(query2);
         o.setInt(1,totalCollected);
         o.setInt(2,id);
         o.execute();

         }
     //////////////////////////////////////////////////////////////////////////////////////
     //this function compare between real consumption and consumption in table
         public boolean validate_reading(int meter_code,int consumption) throws SQLException{
            // int consumption=50;
             int real_consumption;
              
           // Scanner input=new Scanner(System.in);
            //System.out.println("please enter your meter code");
           // meter_code = input.nextInt();
            //this query10 get the consumption from table
             query10="SELECT consumption FROM bill_info WHERE meterCode="+meter_code;
            DataBaseConnection c1=new DataBaseConnection();
            c=c1.connect();
             ss=c.createStatement();
             r=ss.executeQuery(query10);
          r.next();
       real_consumption=r.getInt("consumption");
       if(real_consumption==consumption){ //if they are equal return true
           return true;
       }else{//if they are not change consumption in table with the consumption operator have and return false
        //query13 update value of consumption 
          PreparedStatement k=c.prepareStatement(query13);
         k.setInt(1,consumption);
         k.setInt(2,meter_code);
         k.execute();
         return false;
       }
         }
      ///////////////////////////////////////////////////////////////////////// 
      //this function you enter the meter code and it print the bill
        public Customer print_bill()throws SQLException {
             Scanner input=new Scanner(System.in);
              DataBaseConnection c1=new DataBaseConnection();
              c=c1.connect();
             ss=c.createStatement();
        System.out.println("please enter your meter code");
        int code1 = input.nextInt();
        String fname,lname;
            int cost;
            //this query 11 get the first name,last name,cost
            query11="SELECT * FROM customer_info WHERE meterCode ="+code1;
            r=ss.executeQuery(query11);
       r.next();
       fname=r.getString("fname");
       lname=r.getString("lname");
       cost=tariff_print(code1);
       return new Customer(code1,fname,lname,cost);
        }
        ////////////////////////////////////////////////////////////// 
        //you enter meter code and it delete the customer
       public void cancelCustomerSubscripition(int meterCode) throws SQLException{
           DataBaseConnection c1=new DataBaseConnection();
            c=c1.connect();
         //   System.out.println("please enter your meter code");
         //  Scanner input=new Scanner(System.in);
         //query 14 delete customer from bill_info
        PreparedStatement k=c.prepareStatement(query14);
       // int meterCode = input.nextInt(); 
         k.setInt(1,meterCode);
         k.execute();
         //query 15 delete the customer from customer_info
         PreparedStatement p=c.prepareStatement(query15);
         p.setInt(1,meterCode);
         p.execute();
         //delete customer from bill_info
         PreparedStatement m=c.prepareStatement(query16);
         m.setInt(1,meterCode);
         m.execute();
         
       }
       /////////////////////////////////////////////////////////////
       //view all bills of region
       public ArrayList viewBillsOfRegion() throws SQLException{
            DataBaseConnection c1=new DataBaseConnection();
            c=c1.connect();
            ss=c.createStatement();
           Scanner input=new Scanner(System.in);
            System.out.println("please enter the region :");
        int region = input.nextInt(); 
         query17 ="SELECT * FROM customer_info WHERE Region="+region;
         r=ss.executeQuery(query17);
         int i=1;
         int code;
        ArrayList<Customer> list = new ArrayList<Customer>();
         while( r.next()){
             int cost;
             String f1name,lname;
              code=r.getInt("metercode");
              f1name=r.getString("firstname");
              lname=r.getString("lastname");
              cost=tariff_print(code);
              list.add(new Customer(code,f1name,lname,cost));
         }
         return list;
       }
       public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTotal_collected(int total_collected) {
        this.total_collected = total_collected;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getSsn() {
        return ssn;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public int getTotal_collected() {
        return total_collected;
    }
       
     
       Opertaor(int id,String fname, String lname,String email,String pass,int totalcollected,String ssn){
           this.id = id;
           this.fname = fname;
           this.lname = lname;
           this.ssn = ssn;
           this.email = email;
           this.pass = pass;
           this.total_collected = total_collected;
           
       }
public void updatetariff (int value,int meter)
         {
             DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
       String query; 
       try {
         
           query = "UPDATE bill_info\n" +
"SET triff = ?\n" +
"where metercode =?";
           ss=c.prepareStatement(query);
           ss.setInt (1,value);
           ss.setInt (2,meter);
           ss.execute();
           System.out.println("tariff has been updated");
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
       }
         }
  public void copydata (Opertaor op)
  {
      this.id=op.getId();
  }
}
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
      