package com.ggne.ggneboard.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    // get active profile
    @GetMapping("/profile")
    public String getActiveProfile() {
        return activeProfile;
    }

    // test connection
    @GetMapping("/")
    public String main() {
        return "Hello, World!";
    }
}
