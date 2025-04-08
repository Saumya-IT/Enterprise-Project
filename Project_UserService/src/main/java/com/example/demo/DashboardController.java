package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "user-dashboard";
    }
}
