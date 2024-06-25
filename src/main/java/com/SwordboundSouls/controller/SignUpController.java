package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {
    @Autowired
    private UserService uService;

    @GetMapping("/signUpPage")
    public String signUpPage() {
        return "signUp";
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam("username") String username, @RequestParam("pass") String pass, @RequestParam("buttonPressed") String buttonPressed) {
        ModelAndView modelAndView = new ModelAndView();
        if (usernameIsValid(username) && buttonPressed.equals("confirm")) {
            uService.registerNewUser(new User(username, pass, false));
            modelAndView.setViewName("index");
        } else {
            modelAndView.setViewName("signUp");
        }
        if (buttonPressed.equals("cancel")) {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    public boolean usernameIsValid(String username) {
        boolean isValid = true;
        if (username.equals("") || username.equals("")) {
            isValid = false;
        }
        return isValid;
    }
}
