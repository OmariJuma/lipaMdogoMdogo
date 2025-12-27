package com.example.lipaMdogoMdogo.models.responseDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LoanResDto {
    private UUID id;
    private UUID borrowerId;
    private Double amount;
    private Double paymentPlan;
    private Boolean isApproved;
    private Integer termInMonths;
    //    private ArrayList<Payment> paymentHistory;
    private LocalDateTime issuedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
