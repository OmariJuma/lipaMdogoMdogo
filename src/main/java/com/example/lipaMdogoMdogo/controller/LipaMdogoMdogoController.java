package com.example.lipaMdogoMdogo.controller;

import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.models.User;
import com.example.lipaMdogoMdogo.service.LoanService;
import com.example.lipaMdogoMdogo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/")
public class LipaMdogoMdogoController {
    private final UserService userService;
    private final LoanService loanService;

    public LipaMdogoMdogoController(UserService userService, LoanService loanService) {
        this.userService = userService;
        this.loanService = loanService;
    }

    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User responseBody = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody) ;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("{:id}")
    public ResponseEntity<?> findUserById(@PathVariable UUID id){
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalUser.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+ id +" not found");
    }
    @GetMapping("{:id}/loans")
    public ResponseEntity<List<Loan>> getAllUserLoans(@PathVariable UUID id){
        List<Loan> allUserLoans = loanService.getUserLoans(id);
        return ResponseEntity.status(HttpStatus.OK).body(allUserLoans);
    }
    @GetMapping("{:id}/loans/{:loanId}")
    public ResponseEntity<?> getUserLoan(@PathVariable UUID id, @PathVariable UUID loanId){
        if(loanService.isUserLoan(id, loanId)){
           return ResponseEntity.status(HttpStatus.OK).body(loanService.findLoanById(loanId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Loan "+ loanId+ " does not belong to this user "+ id);

    }

}