package com.example.exercies3.model.entity;

import com.example.exercies3.model.dto.response.EmailResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;

    public EmailResponse toEmailResponse() {
        return new EmailResponse(this.getId(), this.getEmail());
    }

}
