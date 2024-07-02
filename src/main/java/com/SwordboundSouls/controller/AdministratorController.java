package com.SwordboundSouls.controller;

import com.SwordboundSouls.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.SwordboundSouls.helpers.ViewHelper;
import com.SwordboundSouls.service.CharacterService;
import com.SwordboundSouls.service.HollowService;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    private UserService userService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private HollowService hollowService;

    @GetMapping("/users")
    public ModelAndView users(){
        ModelAndView modelAndView = new ModelAndView(ViewHelper.USERS_CRUD);

        modelAndView.addObject("users", userService.getAllUsers());

        return modelAndView;
    }

    @GetMapping("/characters")
    public ModelAndView characters(){
        ModelAndView modelAndView = new ModelAndView(ViewHelper.CHARACTERS_CRUD);

        modelAndView.addObject("characters", characterService.getAllCharacters());

        return modelAndView;
    }

    @GetMapping("/hollows")
    public ModelAndView hollows(){
        ModelAndView modelAndView = new ModelAndView(ViewHelper.HOLLOWS_CRUD);

        modelAndView.addObject("hollows", hollowService.getAllHollows());

        return modelAndView;
    }
}
