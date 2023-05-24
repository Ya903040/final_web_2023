package com.example.storeapp.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author AsiroMan
 */
@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail, String subject, String code, String something){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("addafaidn184@gmail.com");
        message.setTo(toEmail);
        message.setText("thank you");
        message.setSubject(subject+" and ur good's id is: ");
        message.setSubject(code);
        mailSender.send(message);
    }

}
