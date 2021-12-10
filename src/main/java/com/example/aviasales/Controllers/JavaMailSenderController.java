package com.example.aviasales.Controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/mail")
public class JavaMailSenderController {

    @Autowired
    public JavaMailSender emailSender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ResponseEntity<Boolean> Send() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("apanasevich-90@mail.ru");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);
        return new ResponseEntity(true, HttpStatus.OK);
    }

}
