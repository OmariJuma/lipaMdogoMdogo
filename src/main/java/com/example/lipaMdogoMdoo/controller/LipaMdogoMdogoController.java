package com.example.lipaMdogoMdoo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class LipaMdogoMdogoController {
    @GetMapping("users/create")
    public ResponseEntity<ArrayList> getDailyMotivation(@RequestBody User user){
        ArrayList<String> responseBody = new ArrayList<>();
        responseBody.add( "Stay positive and keep pushing forward!");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody) ;
    }
}
