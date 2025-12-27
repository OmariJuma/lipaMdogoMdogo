package com.example.lipaMdogoMdogo.Utilities;

import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.models.User;
import com.example.lipaMdogoMdogo.models.requestDto.LoanReqDto;
import com.example.lipaMdogoMdogo.models.responseDto.LoanResDto;


import java.time.LocalDateTime;

public class Utils {
    public static Loan toLoanEntity(LoanReqDto loanReqDto, User borrower){
        Loan loanRequest = new Loan();
        loanRequest.setAmount(loanReqDto.getAmount());
        loanRequest.setBorrower(borrower);
        loanRequest.setLoanPurpose(loanReqDto.getLoanPurpose());
        loanRequest.setApproverId(loanReqDto.getApproverId());
        loanRequest.setCreatedAt(LocalDateTime.now());
        loanRequest.setUpdatedAt(LocalDateTime.now());
        loanRequest.setPaymentPlan(loanReqDto.getPaymentPlan());
        loanRequest.setTermInMonths(loanReqDto.getTermInMonths());
        return loanRequest;
    }

    public static LoanResDto toLoanDto(Loan loan){
        LoanResDto loanResDto = new LoanResDto();
        loanResDto.setId(loan.getId());
        loanResDto.setBorrowerId(loan.getBorrower().getId());
        loanResDto.setIsApproved(loan.getIsApproved());
        loanResDto.setAmount(loan.getAmount());
        loanResDto.setPaymentPlan(loan.getPaymentPlan());
        loanResDto.setTermInMonths(loan.getTermInMonths());
        loanResDto.setIssuedAt(loan.getIssuedAt());
        loanResDto.setCreatedAt(loan.getCreatedAt());
        loanResDto.setUpdatedAt(loan.getUpdatedAt());
        return loanResDto;
    }

}
