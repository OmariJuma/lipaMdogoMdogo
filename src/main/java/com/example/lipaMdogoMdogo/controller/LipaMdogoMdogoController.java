package com.example.lipaMdogoMdogo.controller;

import com.example.lipaMdogoMdogo.models.Loan;
import com.example.lipaMdogoMdogo.models.User;
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

    public LipaMdogoMdogoController(UserService userService) {
        this.userService = userService;
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

    @GetMapping(":id")
    public ResponseEntity<?> findUserById(@RequestParam UUID id){
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalUser.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+ id +" not found");
    }
    @GetMapping(":id/loans")
    public ResponseEntity<Loan> getAllUserLoans(@RequestParam UUID id){
//        Optional<List<Loan>> allUserLoans =
    }

}