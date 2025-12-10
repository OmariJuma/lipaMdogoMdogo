package com.example.lipaMdogoMdogo.repository;

import com.example.lipaMdogoMdogo.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    List<Loan> findByBorrower_Id(UUID borrowerId);
    Boolean existsByBorrower_IdAndId(UUID borrowerId, UUID id);
}
