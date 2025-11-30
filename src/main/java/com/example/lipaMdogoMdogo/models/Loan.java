package com.example.lipaMdogoMdogo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrowerId", referencedColumnName = "id")
    private User borrower;
    private Double amount;
    private String loanPurpose;
    private Double paymentPlan;
    private Boolean isApproved;
    private UUID approverId;
//    private ArrayList<Payment> paymentHistory;
    private LocalDateTime issuedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
