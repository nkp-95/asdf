package com.example.miniproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }
}