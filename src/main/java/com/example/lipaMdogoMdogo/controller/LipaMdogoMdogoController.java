package com.example.lipaMdogoMdogo.controller;

import com.example.lipaMdogoMdogo.Utilities.Utils;
import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.models.User;
import com.example.lipaMdogoMdogo.models.requestDto.LoanReqDto;
import com.example.lipaMdogoMdogo.service.LoanService;
import com.example.lipaMdogoMdogo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> createUser(@RequestBody User user){
        User responseBody = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody) ;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable UUID id){
       Optional<User> user = userService.findById(id);
       if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+ id +" not found");
    }

    @GetMapping("/{id}/loans")
    public ResponseEntity<?> getAllUserLoans(@PathVariable UUID id){
        List<Loan> allUserLoans = loanService.getUserLoans(id);
        if(!allUserLoans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(allUserLoans);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No loans have been found for this user  " + id);
    }

    @PostMapping("/{id}/loans")
    public ResponseEntity<?> applyLoan(@PathVariable UUID id, @RequestBody LoanReqDto loanReqDto){
        Optional<User> borrower = userService.findById(id);
        if(borrower.isPresent()){
            Loan loanRequest = Utils.toLoanEntity(loanReqDto, borrower.get());
            Loan appliedLoan=  loanService.createLoan(loanRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(appliedLoan);
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
           return ResponseEntity.status(HttpStatus.OK).body(userLoan);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Loan "+ loanId+ " does not belong to this user "+ id);

    }

}