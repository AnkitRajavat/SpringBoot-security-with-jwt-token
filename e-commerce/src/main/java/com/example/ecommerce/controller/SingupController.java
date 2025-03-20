package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingupController {


    @Autowired
    SignupService signupService;

    @PostMapping("/signup")
    public String singup(@RequestBody User user){

    signupService.saveUser(user);
        return "sign up successfully";
    }
    @GetMapping("/signin")
    public String singin(){

        return "sign in up successfully";
    }
    @GetMapping("/create")
    public String create(){

        return "create  successfully";
    }
}
