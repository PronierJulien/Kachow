package com.imt.demo.controller;

import com.imt.demo.exception.MyCustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    
    @GetMapping("/hello/{name}")
    public ResponseEntity<String> sayWelcome(@PathVariable String name) {
        return ResponseEntity.ok("Welcome here " + name);
    }
    
    @GetMapping("/error")
    public ResponseEntity<String> sayError() {
        throw new MyCustomException("c'est une erreur !");
    }
    
}
