package com.example.javamail.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String recipient;
    private String subject;
    private String body;
}

