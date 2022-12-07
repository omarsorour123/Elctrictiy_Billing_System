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
public static void sendMail(String recepient) throws Exception
{ System.out.println("prepare to send email");
    Properties properties = new Properties (); 
    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable","true");
    properties.put("mail.smtp.host","smtp.gmail.com");
    properties.put("mail.smtp.port","587");
    
    String myAccountEmail = "customerproject12@gmail.com";
    String password = "iyiepqpjidqmeodt";    
    
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
        message.setSubject("first message");
        message.setText ("hiiiii ");
        return message ;
    } catch (Exception ex) {
        Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
}