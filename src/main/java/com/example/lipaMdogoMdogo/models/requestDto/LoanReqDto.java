package com.example.lipaMdogoMdogo.models.requestDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoanReqDto {
    private UUID id;
    private Double amount;
    private String loanPurpose;
    private Double paymentPlan;
    private Boolean isApproved;
    private UUID approverId;
    private Integer termInMonths;
}
