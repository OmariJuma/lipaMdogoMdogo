package com.example.lipaMdogoMdogo.models.requestDto;

import lombok.Data;
import java.util.UUID;

@Data
public class LoanReqDto {
    private UUID id;
    private Double amount;
    private String loanPurpose;
    private Double paymentPlan;
    private Boolean isApproved;
    private UUID approverId;
    private Integer termInMonths;
}
