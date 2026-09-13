package com.flowershop.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendingService {
    private final JavaMailSender mailSender;

    @Value("${app.mailing.sender}")
    private String sender;

    public void sendEmailForPasswordRecovery(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("floria password recovery");
        message.setText(token);
        mailSender.send(message);
    }
}
