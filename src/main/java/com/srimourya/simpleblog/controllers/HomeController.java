package com.srimourya.simpleblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    public HomeController() {

    }

    @GetMapping("/getHome")
    public String home() {
        return "home";
    }
}
