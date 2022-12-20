/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;

import static java.lang.Integer.parseInt;
import java.util.Random;
import java.util.regex.Pattern;


public class ValidateInput {
private Integer validateCode;
  // regex for validate email  
  public  boolean validateEmail (String EMail)
    {
return  ( Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$",EMail ));
    }
  //regex for validate ssn
  public  boolean validateSsn (String ssn)
  {
      return  ( Pattern.matches("\\d{14}",ssn ));
  }
  //regex for validate password
   public  boolean validatePassword (String password)
   {
        return  ( Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@_#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",password ));
   }
   // regex for checking number of criedt card
   public  boolean validateCardNumber(String cardNumber)
   {
            return  ( Pattern.matches("\\d{16}",cardNumber ));  
   }
   // regex for checking cvv in the criedt card
   public  boolean validateCvv(String cvv)
   {
         return  ( Pattern.matches("\\d{3}",cvv ));  
   }
   
   
   
   // (to send the validtion code
   public void getValidateCode (String email ) throws Exception
   {  int range = 99999-10000;
   validateCode = (int)(Math.random()*range)+10000;
     JavaMailUtil mail = new   JavaMailUtil ();
   mail.setEmailSubject ("The validetion code");
      mail.setEmailMessage ("your validate code is:\n***********************\n"+validateCode.toString());
         mail.sendMail (email);
   
      
      }
   // to check the code which user entered
public boolean checkCode (String code) 
{
    return (parseInt(code)==validateCode); 
}

    public Integer getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(Integer validateCode) {
        this.validateCode = validateCode;
    }

}
   

