package com.pawsoncall.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.pawsoncall.web.domain.User;
import com.pawsoncall.web.mapper.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public User registerUser(@ModelAttribute User user) {
        if (userService.existsByEmail(user.getEmail())) {
            User userExists = new User();
            userExists.setMetaData("user exist");
            userExists.setEmail(user.getEmail());
            return userExists;
        }
        User usr = userService.registerUser(user);
        usr.setPassword(null);
        return usr;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
