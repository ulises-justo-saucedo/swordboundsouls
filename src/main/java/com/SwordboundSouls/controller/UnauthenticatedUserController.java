package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.helpers.ViewHelper;
import com.SwordboundSouls.service.impl.CharacterService;
import com.SwordboundSouls.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UnauthenticatedUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CharacterService characterService;

    @GetMapping("/login")
    public String login(){
        return ViewHelper.LOGIN;
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @GetMapping("/loginsuccess")
    public ModelAndView loginSuccess() {
        User user = userService.getCurrentAuthenticatedUser();

        if (user.getCharacter() == null)
            return new ModelAndView("redirect:/createcharacter");

        ModelAndView modelAndView = new ModelAndView("redirect:/home");

        modelAndView.addObject("user", user);
        modelAndView.addObject("character", user.getCharacter());

        return modelAndView;
    }

    @GetMapping("/register")
    public String register(){
        return ViewHelper.REGISTER;
    }

    @PostMapping("/registersuccess")
    public ModelAndView registerSuccess(@ModelAttribute User user) {
        if (userService.usernameIsNotValid(user.getPassword()) && userService.passwordIsNotValid(user.getPassword()))
            return new ModelAndView("redirect:/register");

        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        userService.registerNewUser(user);

        return modelAndView;
    }

    @GetMapping("/createcharacter")
    public String createCharacter(){
        return ViewHelper.CREATE_CHARACTER;
    }

    @PostMapping("/createcharactersuccess")
    public ModelAndView createCharacter(@RequestParam("characterName") String characterName, @RequestParam("classType") String classType) {
        if (characterService.getCharacterByName(characterName) != null)
            return new ModelAndView("redirect:/createcharacter");

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        User user = userService.getCurrentAuthenticatedUser();

        Character character = characterService.setCharacterAttributes(characterName, classType, user);
        characterService.createNewCharacter(character);

        user.setCharacter(character);
        userService.updateUser(user);

        modelAndView.addObject("user", user);
        modelAndView.addObject("character", character);

        return modelAndView;
    }
}
