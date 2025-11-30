package com.example.lipaMdogoMdogo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID borrowerId;
    private Double amount;
    private UUID loanId;
    // origin e.g. mobile money, bank, cash etc
    private String paymentOrigin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
