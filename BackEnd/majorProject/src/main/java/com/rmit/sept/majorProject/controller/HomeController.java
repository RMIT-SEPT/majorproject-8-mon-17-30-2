package com.rmit.sept.majorProject.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //Added some temp api's to test login functionality

    @GetMapping("/")
    public String home(){
        return ("<h1> welcome </h1>");
    }

    @GetMapping("/customer")
    public String customer(){
        return ("<h1> welcome customer! </h1>");
    }
    @GetMapping("/admin")
    public String admin(){
        return ("<h1> hello admin </h1>");
    }

}
