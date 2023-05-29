package com.example.storeapp.controller;

import com.example.storeapp.global.GlobalData;
import com.example.storeapp.model.Role;
import com.example.storeapp.model.User;
import com.example.storeapp.repository.RoleRepository;
import com.example.storeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/login")
    public String login() {
        GlobalData.cart.clear();
        return "login";
    }


    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }


    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {


        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode((password)));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);

        request.login(user.getEmail(), password);
        return "redirect:/";


    }

}

