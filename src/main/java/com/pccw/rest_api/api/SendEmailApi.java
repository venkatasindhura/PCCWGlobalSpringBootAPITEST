package com.pccw.rest_api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.core.io.ClassPathResource;

import com.pccw.rest_api.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class SendEmailApi{

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(User user) {
        String recipientAddress = user.getEmail();
        System.out.printf("#############%s", recipientAddress);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(recipientAddress);
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");
        javaMailSender.send(msg);
    }
    public void sendEmailWithAttachment(String email) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo(email);

        helper.setSubject("Testing from Spring Boot");
        // default = text/plain
        //helper.setText("Check attachment for image!");
        // true = text/html
        helper.setText("<h1>Registeration Success!</h1>", true);
		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
        // helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(msg);
    }
}