package com.pawsoncall.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@RequestMapping(method = RequestMethod.GET)
@RestController
public class HomeController {
    public String home(@AuthenticationPrincipal OidcUser user) {
        return "Welcome, " + user.getFullName() + "!";
    }
}
