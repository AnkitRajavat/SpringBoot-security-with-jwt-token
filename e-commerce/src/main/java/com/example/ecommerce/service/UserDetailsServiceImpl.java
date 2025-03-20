package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byPhone = userRepository.findByPhone(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(byPhone!=null){
            UserDetails user = org.springframework.security.core.userdetails.User.builder()
                    .username(byPhone.getPhone())
                    .password(byPhone.getPassword())
                    .roles("User")
                    .build();
            return user;
        }
        return null;
    }
}
