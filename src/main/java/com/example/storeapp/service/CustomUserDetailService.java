package com.example.storeapp.service;

import com.example.storeapp.model.CustomUserDetail;
import com.example.storeapp.model.User;
import com.example.storeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional <User> user = userRepository.findUsersByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("error"));
        return user.map(CustomUserDetail::new).get();

    }
}
