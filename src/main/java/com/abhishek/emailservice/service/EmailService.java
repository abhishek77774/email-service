package com.abhishek.emailservice.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    @Value("${SENDGRID_API_KEY}")
    private String apiKey;

    public void sendText(String from, String to, String subject, String body) throws IOException {
        Response response = sendEmail(from, to, subject, body);
        System.out.println("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: "
                + response.getHeaders());
    }

    private Response sendEmail(String from, String to, String subject, String message) throws IOException {
        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();    
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        return sg.api(request);
    }
}