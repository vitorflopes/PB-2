package com.example.javamail.controller;

import com.example.javamail.DTO.EmailDTO;
import com.example.javamail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }



    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) {
        emailService.enviarEmail(emailDTO);
        return ResponseEntity.ok("Email enviado com sucesso!");
    }


}

