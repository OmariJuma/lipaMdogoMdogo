package com.example.lipaMdogoMdogo.models.responseDto;

import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.models.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserResDto {
    private UUID id;
    private String firstName;
    private String secondName;
    private String idNo;
    private String msisdn;
    private Double creditLimit;
    private Integer totalLoansRequested;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

