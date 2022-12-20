package com.mycompany.elctricitybillingsystem;
import java.sql.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Customer {
  private  int meterCode;
   private String F_name;
   private String L_name;
   private int Region;
   private String Email;
   private String Password;
  private  String phoneNumber;
   private int tariff;
    private int  consumption;
  private  String SSN;
     java.sql.Date  payingDate;
   
     
     
     public Customer() {
    
}
 
                                       
                                
    public Customer(int meterCode, String F_name, String L_name, int Region, String Email, String Password, String phoneNumber, int tariff, int consumption, String SSN, Date payingDate) {
      this.meterCode= meterCode;
        this.F_name = F_name;
        this.L_name = L_name;
        this.Region = Region;
        this.Email = Email;
        this.Password = Password;
        this.phoneNumber = phoneNumber;
        this.tariff = tariff;
        this.consumption = consumption;
        this.SSN = SSN;
        this.payingDate = payingDate;
    }

    public void setMeterCode(int meterCode) {
        this.meterCode = meterCode;
    }

    public void setF_name(String F_name) {
        this.F_name = F_name;
    }

    public void setL_name(String L_name) {
        this.L_name = L_name;
    }

    public void setRegion(int Region) {
        this.Region = Region;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setPayingDate(Date payingDate) {
        this.payingDate = payingDate;
    }

    public int getMeterCode() {
        return meterCode;
    }

    public String getF_name() {
        return F_name;
    }

    public String getL_name() {
        return L_name;
    }

    public int getRegion() {
        return Region;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getTariff() {
        return tariff;
    }

    public int getConsumption() {
        return consumption;
    }

    public String getSSN() {
        return SSN;
    }

    public Date getPayingDate() {
        return payingDate;
    }
    public void copyData (Customer temp )
    {
        this.meterCode = temp.meterCode;
        this.F_name = temp.F_name;
        this.L_name = temp.L_name;
        this.Region = temp.Region;
        this.Email = temp.Email;
        this.Password = temp.Password;
        this.phoneNumber = temp.phoneNumber;
        this.tariff = temp.tariff;
        this.consumption = temp.consumption;
        this.SSN = temp.SSN;
        this.payingDate = temp.payingDate;
    }
     @Override
    public String toString() {
        return "Customer{" + "meterCode=" + meterCode + ", F_name=" + F_name + ", L_name=" + L_name + ", Region=" + Region + ", Email=" + Email + ", Password=" + Password + ", phoneNumber=" + phoneNumber + ", tariff=" + tariff + ", consumption=" + consumption + ", SSN=" + SSN + ", payingDate=" + payingDate + '}';
    }
      public Customer(  String firstName,  String LastName,    String Email ,   String passWord ,  int region  ){
   
     this.F_name = firstName;
     this.L_name = LastName;
     this.Email = Email;
     this.Password = passWord;
     this.Region = region;

     java.sql.Date date= new Date (System.currentTimeMillis());
    
    this.payingDate=date;
    this.SSN="12313213";
    this.consumption=5;
    this.tariff=5;
    this.phoneNumber="011";
   }
     public Customer(int meterCode, String F_name, String L_name, int tariff) {
        this.meterCode = meterCode;
        this.F_name = F_name;
        this.L_name = L_name;
        this.tariff = tariff;
    }
     public void customerComplain (String complain) 
     { try  {
         JavaMailUtil ma = new JavaMailUtil();
         ma.setEmailMessage (complain);
         ma.setEmailSubject("complain");
         ma.sendMail ("electricalbillingsystem@gmail.com");
     }
     catch (Exception e)
     { 
         System.out.println(e.getMessage());
     }
     }
}
