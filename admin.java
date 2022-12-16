/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import static javaapplication1.opertaor.query;
import static javaapplication1.opertaor.query17;
import static javaapplication1.opertaor.ss;

/**
 *
 * @author omars
 */


public class admin {
    static Statement ll;
    static Connection c;
    static Connection k;
    static ResultSet r;
    static ResultSet z;
    static ResultSet l;
    static ResultSet x;
    static String query;
    static String query2;
    static String query3;
     static String query4;
     static String query5;
    public void deleteCustomer (int meeterCode ) throws SQLException
    {  sql c1=new sql();
       c=c1.connect(); 
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
    admin(){
        
    }
    
    public void addNewCustomer (customer customer) throws SQLException
    {
        int m = 0;
          sql c1=new sql();
       c=c1.connect(); 
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
           ss.setDate(1, (Date) customer.getPayingDate());
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
    
    
    public void updateCustomer (int meterCode , String feild,int value) throws SQLException
    {
         sql c1=new sql();
       c=c1.connect(); 
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
        sql c1=new sql();
       c=c1.connect(); 
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
     public ArrayList<customer> viewBillsOfRegion() throws SQLException{
            sql c1=new sql();
            c=c1.connect();
            ss=c.createStatement();
           Scanner input=new Scanner(System.in);
            System.out.println("please enter the region :");
        int region = input.nextInt(); 
         query2 ="SELECT * FROM customer_info WHERE Region="+region;
         r=ss.executeQuery(query2);
         int code;
         opertaor op1=new opertaor();
        ArrayList<customer> list = new ArrayList<customer>();
         while( r.next()){
             int cost;
             String f1name,lname;
              code=r.getInt("metercode");
              f1name=r.getString("fname");
              lname=r.getString("lname");
              cost=op1.tariff_print(code);
              list.add(new customer(code,f1name,lname,cost));
              
         }
         return list;
       }
     public int totalCollected() throws SQLException{
     sql c1=new sql();
     c=c1.connect();
     ss=c.createStatement();
     query2 ="SELECT collected_payment FROM operator_payment ";
       r=ss.executeQuery(query2);
       int totalCollected=0;
       while(r.next()){
          totalCollected+=r.getInt("collected_payment");
       }
        return totalCollected;
         
     }
     public void viewStatistics() throws SQLException {
        double sum = 0;
        double sumAllRegions = 0, result;
       
        Scanner input=new Scanner(System.in);
        int region;
        System.out.print("please enter the region : ");
        region = input.nextInt();

        result = viewStatisticsOfSpecifecRegionGui(region) ;
        System.out.println("the total consumption for the " + region + " is " + result + "%");

    }
    public double viewStatisticsOfSpecifecRegionGui( int region) throws SQLException{
        double sum = 0;
        double sumAllRegions = 0, result;
        int meterCode ;
         sql c1=new sql();
         sql c2=new sql();
            c=c1.connect();
            k=c1.connect();
            ll=c.createStatement();
            ss=c.createStatement();
            
            query3="select consumption from bill_info ";
      z=ss.executeQuery(query3);
        while( z.next()) {
            sumAllRegions+=z.getInt("consumption");
        }
        query4="select metercode from customer_info where Region="+region;
        x=ss.executeQuery(query4);
        while(x.next()){
         
           meterCode= x.getInt("metercode");
           
            query5="select consumption from bill_info where metercode3 = "+meterCode;
            l=ll.executeQuery(query5);
            
            l.next();
            sum+=l.getInt("consumption");
               System.out.println("1");
        }
        System.out.println("sum="+sum+"sumAllregion="+sumAllRegions);
        result = sum / sumAllRegions;
        return result*100 ;
        
    }
     
}
