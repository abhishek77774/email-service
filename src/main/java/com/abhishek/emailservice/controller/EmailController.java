package com.abhishek.emailservice.controller;

import com.abhishek.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/send-text")
    public void send() throws IOException {

        //using default values
         emailService.sendText("abhisharma77774@gmail.com",
                "abhishek611sharma@gmail.com",
                "Test",
                "This is a test email");
    }

}
