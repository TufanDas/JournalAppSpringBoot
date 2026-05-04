package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChackController {

    @GetMapping("/health-check")
    public  String healthCheck(){
        return  "OK OK"; // autoconvert into json data
    }
}
