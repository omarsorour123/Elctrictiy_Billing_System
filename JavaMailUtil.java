/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elctricitybillingsystem;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





public class JavaMailUtil {
private static String emailMessage;
private static String emailSub;
public static void sendMail(String recepient) throws Exception
{ System.out.println("prepare to send email");
    Properties properties = new Properties (); 
    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable","true");
    properties.put("mail.smtp.host","smtp.gmail.com");
    properties.put("mail.smtp.port","587");
    
    String myAccountEmail = "electricalbillingsystem@gmail.com";
    String password = "wvkfwecfzcrrovcf";    
    
    Session session = Session.getInstance(properties,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
          });
Message message = prepareMessage (session,myAccountEmail,recepient);
Transport.send(message);
    System.out.println("message sent sucssesfully");
}

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
    try {
        Message message = new MimeMessage  (session);
        message.setFrom(new InternetAddress (myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject(emailSub);
        message.setText (emailMessage);
        return message ;
    } catch (Exception ex) {
        Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    public static void setEmailMessage (String emailMessage)
    {
        JavaMailUtil.emailMessage=emailMessage;
    }
     public static void setEmailSubject (String emailSub)
     {
         JavaMailUtil.emailSub = emailSub;
     }
}