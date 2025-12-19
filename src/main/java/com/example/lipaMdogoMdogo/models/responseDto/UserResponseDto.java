package com.example.lipaMdogoMdogo.models.responseDto;

import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.models.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserResponseDto{
    private Header header;
    private UserDto UserDto;

    @Data
    @Builder
    public static class Header{
        private UUID requestId;
        private LocalDateTime completedAt;
        private Integer statusCode;
        private String message;
    }

    @Data
    @Builder
    public static class UserDto {
        private UUID id;
        private String firstName;
        private String secondName;
        private String idNo;
        private String msisdn;
        private Double creditLimit;
        private List<Loan> loans = new ArrayList<>();
        private Role role;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}

