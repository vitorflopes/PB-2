package com.example.javamail.service;

import com.example.javamail.DTO.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDTO.getRecipient());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getBody());

        // Envia o email usando JavaMail
        mailSender.send(message);
        System.out.println("E-mail enviado com sucesso para: " + emailDTO.getRecipient());
    }
}
