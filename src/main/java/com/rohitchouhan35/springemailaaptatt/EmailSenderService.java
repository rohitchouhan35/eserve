package com.rohitchouhan35.springemailaaptatt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String weatherDataContent
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rohit.captain1509@gmail.com");
        message.setTo(toEmail);
        message.setText(weatherDataContent);
        message.setSubject(subject);
        mailSender.send(message);
        String userName = toEmail.substring(0, toEmail.indexOf('@'));
        System.out.println("Sent Successfully to " + userName);
    }

    }
