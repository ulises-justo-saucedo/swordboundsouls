package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.helpers.ViewHelper;
import com.SwordboundSouls.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticatedUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView(ViewHelper.HOME);
        User user = userService.getCurrentAuthenticatedUser();

        modelAndView.addObject("user", user);
        modelAndView.addObject("character", user.getCharacter());

        return modelAndView;
    }

    @GetMapping("/train")
    public String train(){
        return ViewHelper.TRAIN;
    }

    @GetMapping("/worldbosses")
    public String worldBosses(){
        return ViewHelper.WORLD_BOSSES;
    }

    @GetMapping("/shop")
    public String shop(){
        return ViewHelper.SHOP;
    }
}
