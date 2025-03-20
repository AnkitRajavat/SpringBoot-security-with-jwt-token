package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {

public final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;
    @Override
    public void saveUser(User user) {
user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }
}
