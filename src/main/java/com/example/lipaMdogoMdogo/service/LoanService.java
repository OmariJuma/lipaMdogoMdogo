package com.example.lipaMdogoMdogo.service;

import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    public LoanService(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }
    public List<Loan> getUserLoans(UUID id){
       return loanRepository.findByBorrower_Id(id);
    }

    public Optional<Loan> findLoanById(UUID id){
        return loanRepository.findById(id);
    }

    public Loan createLoan(Loan loan){
        return loanRepository.save(loan);
    }

    public Boolean isUserLoan(UUID borrowerId, UUID loanId){
        return loanRepository.existsByBorrower_IdAndId(borrowerId, loanId);
    }
}
