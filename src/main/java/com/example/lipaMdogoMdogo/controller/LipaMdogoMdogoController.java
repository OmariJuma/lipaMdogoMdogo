package com.example.lipaMdogoMdogo.controller;

import com.example.lipaMdogoMdogo.models.User;
import com.example.lipaMdogoMdogo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class LipaMdogoMdogoController {
    private final UserService userService;

    public LipaMdogoMdogoController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User responseBody = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody) ;
    }
}
