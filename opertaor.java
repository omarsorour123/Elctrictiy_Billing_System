/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.sql.Statement;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static javaapplication1.JavaApplication1.c;
import static javaapplication1.JavaApplication1.query;
import static javaapplication1.JavaApplication1.r;
import static javaapplication1.JavaApplication1.ss;
import java.sql.PreparedStatement;
import static javaapplication1.JavaApplication1.query6;
import static javaapplication1.JavaApplication1.r;
import static javaapplication1.JavaApplication1.ss;
/**
 *
 * @author omar
 */
public class opertaor {
    static Statement ss;
    static String query;//used in traiff and traiff_print
    static String query2="UPDATE operator_payment SET collected_payment=? WHERE ID=?";//collectedPayment
    static String query3;//used in traiff
    static String query4="UPDATE bill_info SET triff=? WHERE metercode3=?";//traiff
    static String query9;//collected payment
    static String query10;//validate
    static String query11;//print_bill
    static String query13="UPDATE bill_info SET consumption=? WHERE metercode3=?";//validate
    static String query14="delete from bill_info where metercode3=?";//cancelsubscribation
    static String query15="delete from customer_info where metercode=?";//cancelsubscribation
    static String query16="delete from customer_contact where Metercode=?";//cancelsubscribation
    static String query17;//show region bill
    static Connection c;
    static ResultSet r;
    static ResultSet l;
    
    //function to calculate traiff
     int tariff() throws SQLException {
         int traiff;
         int firstCard=9,secondCard=13,thirdCard=20;
         Scanner input=new Scanner(System.in);
        System.out.println("please enter your meter code");
        int code = input.nextInt(); 
         sql c1=new sql();
         c=c1.connect();
         ss=c.createStatement();
         //query to get consumption by metercode
         query="SELECT consumption FROM bill_info WHERE metercode3="+code;
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
       query3="UPDATE bill_info SET consumption=0 WHERE metercode3= "+code;
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
      int tariff_print(int code) throws SQLException {
         int traiff, firstCard=9,secondCard=13,thirdCard=20;
        sql c1=new sql();
        c=c1.connect();
         ss=c.createStatement();
         //this query is the same from traiff function
         query="SELECT consumption FROM bill_info WHERE metercode3="+code;
         l=ss.executeQuery(query);
       l.next();
       traiff=l.getInt("consumption");
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
     public void collectPayment() throws SQLException{
         int tariff;
         //we use function traiff
         tariff = tariff();
         Scanner input=new Scanner(System.in);
         System.out.println("enter the id:");
         int id= input.nextInt();//we take id from opertor 
         //query9 this query we take the collected payment from the operator to add it to the newpayment
         query9="SELECT collected_payment FROM operator_payment WHERE ID="+id;
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
         public boolean validate_reading() throws SQLException{
             int consumption=50;
             int real_consumption;
             int meter_code;
            Scanner input=new Scanner(System.in);
            System.out.println("please enter your meter code");
            meter_code = input.nextInt();
            //this query10 get the consumption from table
             query10="SELECT consumption FROM bill_info WHERE metercode3="+meter_code;
             sql c1=new sql();
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
        public customer print_bill()throws SQLException {
             Scanner input=new Scanner(System.in);
              sql c1=new sql();
              c=c1.connect();
             ss=c.createStatement();
        System.out.println("please enter your meter code");
        int code1 = input.nextInt();
        String fname,lname;
            int cost;
            //this query 11 get the first name,last name,cost
            query11="SELECT * FROM customer_info WHERE metercode ="+code1;
            r=ss.executeQuery(query11);
       r.next();
       fname=r.getString("fname");
       lname=r.getString("lname");
       cost=tariff_print(code1);
       return new customer(code1,fname,lname,cost);
        }
        ////////////////////////////////////////////////////////////// 
        //you enter meter code and it delete the customer
       public void cancelCustomerSubscripition() throws SQLException{
           sql c1=new sql();
            c=c1.connect();
            System.out.println("please enter your meter code");
           Scanner input=new Scanner(System.in);
         //query 14 delete customer from bill_info
        PreparedStatement k=c.prepareStatement(query14);
        int meterCode = input.nextInt(); 
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
       public void viewBillsOfRegion() throws SQLException{
            sql c1=new sql();
            c=c1.connect();
            ss=c.createStatement();
           Scanner input=new Scanner(System.in);
            System.out.println("please enter the region :");
        int region = input.nextInt(); 
         query17 ="SELECT * FROM customer_info WHERE Region="+region;
         r=ss.executeQuery(query17);
         int i=1;
         int code;
        ArrayList<customer> list = new ArrayList<customer>();
         while( r.next()){
             int cost;
             String f1name,lname;
              code=r.getInt("metercode");
              f1name=r.getString("fname");
              lname=r.getString("lname");
              cost=tariff_print(code);
              list.add(new customer(code,f1name,lname,cost));
         }
       }

}
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
      