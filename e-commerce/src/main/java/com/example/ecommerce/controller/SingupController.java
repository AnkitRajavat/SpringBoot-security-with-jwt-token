package com.example.ecommerce.controller;

import com.example.ecommerce.config.JwtUtil;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.SignupService;
import com.example.ecommerce.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SingupController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceimpl;
    
    @Autowired
    SignupService signupService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String singup(@RequestBody User user){

    signupService.saveUser(user);
        return "sign up successfully";
    }
    @GetMapping("/login")
    public ResponseEntity<?> singin(@RequestBody User user){
        try{
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getPhone(),user.getPassword()));

        UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername(user.getPhone());
        String jwt =jwtUtil.generateToken(userDetails.getUsername());

        return new ResponseEntity<>(jwt, HttpStatus.OK);

        } catch (Exception e) {
            log.info("exception is {}",e.getMessage());
          throw  new  RuntimeException("Invalid username or password");

        }
    }
    @GetMapping("/create")
    public String create(){

        return "create  successfully";
    }
}
