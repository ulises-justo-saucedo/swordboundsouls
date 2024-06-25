package com.SwordboundSouls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.SwordboundSouls.service.CharacterService;
import com.SwordboundSouls.service.HollowService;
import com.SwordboundSouls.service.UserService;

@Controller
public class AdministratorController {
    @Autowired
    private UserService userService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private HollowService hollowService;

    @PostMapping("/manageAdminWebPages")
    public ModelAndView manageAdminWebPages(@RequestParam("username") String username, @RequestParam("characterName") String characterName, @RequestParam("webPage") String webPage) {
        ModelAndView modelAndView = new ModelAndView();
        switch (webPage) {
            case "usersCrud":
                modelAndView.addObject("user", userService.getUser(username));
                modelAndView.addObject("character", characterService.getCharacterByName(characterName));
                modelAndView.addObject("allUsers", userService.getAllUsers());
                modelAndView.setViewName("usersCrud");
                break;
            case "charactersCrud":
                modelAndView.addObject("user", userService.getUser(username));
                modelAndView.addObject("character", characterService.getCharacterByName(characterName));
                modelAndView.addObject("allCharacters", characterService.getAllCharacters());
                modelAndView.setViewName("charactersCrud");
                break;
            case "hollowsCrud":
                break;
        }
        return modelAndView;
    }

}
