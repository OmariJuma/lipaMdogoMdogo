package com.example.lipaMdogoMdogo.repository;

import com.example.lipaMdogoMdogo.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
}
