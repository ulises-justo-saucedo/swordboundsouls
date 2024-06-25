package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/logIn")
    public ModelAndView logIn(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (userService.getUser(user.getUsername()) != null) {
            if (userService.getUser(user.getUsername()).getCharacter() != null) {
                modelAndView.addObject("user", userService.getUser(user.getUsername()));
                modelAndView.addObject("character", userService.getUser(user.getUsername()).getCharacter());
                modelAndView.setViewName("home");
            } else {
                modelAndView.addObject("username", user.getUsername());
                modelAndView.setViewName("createCharacter");
            }
        }
        return modelAndView;
    }
}
