/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;

import static com.mycompany.elctricitybillingsystem.Opertaor.c;
import static com.mycompany.elctricitybillingsystem.Opertaor.ss;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author omars
 */


public class Admin {
  private int a_id;
  private String email,password;

    public Admin(int a_id, String email, String password) {
        this.a_id = a_id;
        this.email = email;
        this.password = password;
    }
  public Admin (){
    
}
   // to delete the customer  
  public void deleteCustomer (int meeterCode )
    {  DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
    
         try {
    String query;
      // to delete customer (query)   
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
            customer.setMeterCode(m);
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
             System.out.println("error");
         }
    }
    
    
    public void updateCustomer (int meterCode ,int value)
    {
         DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       Statement ss;
         ResultSet r;
         try {
    String query;
    

        query = "update bill_info set consumption="+value+" where metercode ="+meterCode;
            // ss=c.prepareStatement(query);
              ss=c.createStatement();
             //ss.setInt(1, value);
             //ss.setInt (2,meterCode);
             ss.execute(query);
            
         }
         catch (SQLException e)
         { System.out.println("shit");
             System.out.println(e.getMessage());
         }
                
    }
    
    
     public void updateCustomer (   int meterCode , String feild,java.sql.Date x)
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
     public void addOperator (Opertaor operator)
     {
         DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
        ResultSet r;
        String query;
        try {
         query = "insert into operator_info (fname,lname,ssn,email,password)"
                 + "values (?,?,?,?,?)";
         
            ss=c.prepareStatement(query);
            ss.setString (1,operator.getFname());
            ss.setString(2, operator.getLname());
            ss.setString(3,operator.getSsn());
            ss.setString (4,operator.getEmail());
            ss.setString(5, operator.getPass());
            ss.execute();
             query = "select MAX(id) as max_id from operator_info";
                ss=c.prepareStatement(query);
             r=ss.executeQuery();
             r.next();
             
             int m =r.getInt(1);
             query="insert into operator_payment (id,collected_payment)"
                     + "values (?,0)";
               ss=c.prepareStatement(query);
             ss.setInt (1,m);
           
               ss.execute();
        }
        
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
     }
     public void updateOperator (int id,String feild,String value)
     {
         DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
       String query; 
       try {
           query = "update operator_info\n"
                   + "set "+feild+" = ? "
                   + "where id = ?";
           ss=c.prepareStatement(query);
          ss.setString(1,value);
          ss.setInt (2,id);
           ss.execute();
           System.out.println("account has been updated");
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
       }
     }
     public void deleteOperator (int id)
     {
         DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
       String query; 
       try {
           query = "delete from operator_info where id=?";
           ss=c.prepareStatement(query);
           ss.setInt (1,id);
           ss.execute();
           System.out.println("account has been deleted");
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
       }
     }
     public void addAdmin (Admin admin )
     { 
          DataBaseConnection d = new DataBaseConnection ();
        Connection c ;
        c=d.connect() ;
       PreparedStatement ss;
       String query; 
       try {
           query = "insert into admin(email,password) "
                   + "values(?,?)";
           ss=c.prepareStatement(query);
               ss.setString (1,admin.email);
               ss.setString (2,admin.password);
           ss.execute();
           System.out.println("account added");
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
       }
     }
      public int totalCollected() throws SQLException{
     DataBaseConnection c1=new DataBaseConnection();
     Connection c=c1.connect();
    Statement ss=c.createStatement();
    String query2 ="SELECT collected_payment FROM operator_payment ";
      ResultSet r=ss.executeQuery(query2);
       int totalCollected=0;
       while(r.next()){
          totalCollected+=r.getInt("collected_payment");
       }
        return totalCollected;
         
     }
      public ArrayList<Customer> viewBillsOfRegion(int region) throws SQLException{
           DataBaseConnection c1=new DataBaseConnection();
            Connection c=c1.connect();
            Statement ss=c.createStatement();
           Scanner input=new Scanner(System.in);
            System.out.println("please enter the region :");
        
     String    query2 ="SELECT * FROM customer_info WHERE Region="+region;
     ResultSet    r=ss.executeQuery(query2);
         int code;
         Opertaor op1=new Opertaor();
        ArrayList<Customer> list = new ArrayList<Customer>();
         while( r.next()){
             int cost;
             String f1name,lname;
              code=r.getInt("metercode");
              f1name=r.getString("firstname");
              lname=r.getString("lastname");
              cost=op1.tariff_print(code);
              list.add(new Customer(code,f1name,lname,cost));
              
         }
          return list;
     
}
       public double viewStatisticsOfSpecifecRegionGui( int region) throws SQLException{
        double sum = 0;
        double sumAllRegions = 0, result;
        int meterCode ;
        DataBaseConnection c1=new DataBaseConnection();
         DataBaseConnection c2=new DataBaseConnection();
           Connection c=c1.connect();
            c1.connect();
           Statement ll=c.createStatement();
           Statement ss=c.createStatement();
            
           String query3="select consumption from bill_info ";
    ResultSet  z=ss.executeQuery(query3);
        while( z.next()) {
            sumAllRegions+=z.getInt("consumption");
         //   System.out.println(sumAllRegions);
        }
  String query4="select meterCode from customer_info where region="+region;
      ResultSet  x=ss.executeQuery(query4);
        while(x.next()){
         
           meterCode= x.getInt("meterCode");
          // meterCode=2022008;
       //     System.out.println("meterCode");
         String query5="select consumption from bill_info where metercode = "+meterCode;
          ResultSet  l=ll.executeQuery(query5);
           
            l.next();
            sum+=l.getInt("consumption");
              
        }
        result = sum / sumAllRegions;
        return result*100 ;
        
    }
    
  public void viewStatistics() throws SQLException {
        double sum = 0;
        double sumAllRegions = 0, result;
       
        Scanner input=new Scanner(System.in);
        int region;
        System.out.print("please enter the region : ");
        region = input.nextInt();

        result = viewStatisticsOfSpecifecRegionGui(region) ;
        System.out.format("the total consumption for the %d is %.2f" ,region, result );
        System.out.println("%");

    }     
  public void filltableI(JTable table,int reigon) throws SQLException
  {
     ArrayList <Customer>  arr = new ArrayList ();
     arr=viewBillsOfRegion(reigon);
     for(int i=0;i<arr.size();i++)
     {    System.out.println(arr.get(i).getF_name());  
         DefaultTableModel dtm = (DefaultTableModel) table.getModel();
         Integer meter= arr.get(i).getMeterCode();
         Integer pay = arr.get(i).getTariff();
     String tbData []= {arr.get(i).getF_name(),arr.get(i).getL_name(),meter.toString(),pay.toString()};
    dtm.addRow(tbData);
     
     }
  }
           public void updatecons (int value,int meter)
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

}