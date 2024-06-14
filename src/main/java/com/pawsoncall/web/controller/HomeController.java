package com.pawsoncall.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/todo")
@RestController
public class HomeController {
    @GetMapping(path = "/todo")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        return "Welcome, " + principal.getAttribute("name") + "!";
    }
}
