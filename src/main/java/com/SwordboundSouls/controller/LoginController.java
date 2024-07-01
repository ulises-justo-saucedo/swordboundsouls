package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @PostMapping("/logout")
    public String logout(){
        return "index";
    }

    @GetMapping("/loginsuccess")
    public ModelAndView logIn() {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("index");
        if (user.getCharacter() != null) {
            modelAndView.addObject("user", user);
            modelAndView.addObject("character", user.getCharacter());
            modelAndView.setViewName("home");
        } else {
            modelAndView.addObject("username", user.getUsername());
            modelAndView.setViewName("createCharacter");
        }
        return modelAndView;
    }
}
