package com.mycompany.elctricitybillingsystem;
import java.util.*;

public class Person {
    private String name;
    private String email;
    private String password;
    private String ssn;

    public Person(String name, String email, String password, String ssn) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.ssn = ssn;
    }
 public Person ()
 {
     
 }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "Name=" + name + ", Email= " + email + ", Password=" + password + ", Ssn=" + ssn + '}';
    }

   
       
   
}
