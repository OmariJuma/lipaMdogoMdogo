package com.example.lipaMdogoMdogo.controller;

import com.example.lipaMdogoMdogo.Utilities.Utils;
import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.models.User;
import com.example.lipaMdogoMdogo.models.requestDto.LoanReqDto;
import com.example.lipaMdogoMdogo.models.responseDto.LoanResDto;
import com.example.lipaMdogoMdogo.models.responseDto.UserResDto;
import com.example.lipaMdogoMdogo.service.LoanService;
import com.example.lipaMdogoMdogo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class LipaMdogoMdogoController {
    private final UserService userService;
    private final LoanService loanService;

    public LipaMdogoMdogoController(UserService userService, LoanService loanService) {
        this.userService = userService;
        this.loanService = loanService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResDto> createUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        UserResDto newUserDto = Utils.toUserResDto(newUser);
        return ResponseEntity.status(HttpStatus.OK).body(newUserDto) ;
    }

    @GetMapping
    public ResponseEntity<List<UserResDto>> getAllUsers(){
        List<UserResDto> userResDtos = userService.getAllUsers().stream().map(Utils::toUserResDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(userResDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable UUID id){
       Optional<User> user = userService.findById(id);
       if(user.isPresent()){
           UserResDto userResDto = Utils.toUserResDto(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(userResDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+ id +" not found");
    }

    @GetMapping("/{id}/loans")
    public ResponseEntity<?> getAllUserLoans(@PathVariable UUID id){
        List<Loan> allUserLoans = loanService.getUserLoans(id);
        if(!allUserLoans.isEmpty()) {
            List<LoanResDto> loansDtos = allUserLoans.stream().map(Utils::toLoanDto).toList();
            return ResponseEntity.status(HttpStatus.OK).body(loansDtos);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No loans have been found for this user  " + id);
    }

    @PostMapping("/{id}/loans")
    public ResponseEntity<?> applyLoan(@PathVariable UUID id, @RequestBody LoanReqDto loanReqDto){
        Optional<User> borrower = userService.findById(id);
        if(borrower.isPresent()){
            Loan loanRequest = Utils.toLoanEntity(loanReqDto, borrower.get());
            Loan appliedLoan=  loanService.createLoan(loanRequest);
            LoanResDto appliedLoanDto = Utils.toLoanDto(appliedLoan);
            return ResponseEntity.status(HttpStatus.CREATED).body(appliedLoanDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+ id +" not found");
    }

    @GetMapping("/{id}/loans/{loanId}")
    public ResponseEntity<?> getUserLoan(@PathVariable UUID id, @PathVariable UUID loanId){
        Optional<Loan> userLoan = loanService.findLoanById(loanId);
        boolean isEmpty = userLoan.isEmpty();
        if(isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan " + id +" not found");
        }
        else if(loanService.isUserLoan(id, loanId)){
            LoanResDto userLoanDto = Utils.toLoanDto(userLoan.get());
           return ResponseEntity.status(HttpStatus.OK).body(userLoanDto);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Loan "+ loanId+ " does not belong to this user "+ id);

    }

}