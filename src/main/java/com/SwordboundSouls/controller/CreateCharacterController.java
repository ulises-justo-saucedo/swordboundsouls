package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.service.CharacterService;
import com.SwordboundSouls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class CreateCharacterController {
    @Autowired
    private UserService userService;

    @Autowired
    private CharacterService characterService;

    @GetMapping("/createCharacter")
    public ModelAndView createCharacter(@RequestParam("characterName") String characterName, @RequestParam("classType") String classType, @RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        if (characterService.getCharacterByName(characterName) != null) {
            //Name already taken. User has to choose another
            modelAndView.addObject("username", username);
            modelAndView.setViewName("createCharacter");
        } else {
            Character newCharacter = setCharacterAttributes(characterName, classType, userService.getUser(username));
            User user = userService.getUser(username);
            user.setCharacter(newCharacter);
            characterService.createNewCharacter(newCharacter);
            userService.registerNewUser(user);
            modelAndView.addObject("user", user);
            modelAndView.addObject("character", newCharacter);
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

    public Character setCharacterAttributes(String characterName, String classType, User user) {
        HashMap<String, Integer> characterStats = new HashMap<>();
        String characterAspect = "ImageHere";
        switch (classType) {
            case "berserker":
                characterStats = setCharacterStats(20, 15, 15, 5);
                characterAspect = "https://i.imgur.com/pbwUTSW.png";
                break;
            case "balanced":
                characterStats = setCharacterStats(15, 10, 10, 10);
                characterAspect = "https://i.imgur.com/8noIjw3.jpg";
                break;
            case "spiritual":
                characterStats = setCharacterStats(10, 5, 5, 20);
                characterAspect = "https://i.imgur.com/S83m0qD.png";
                break;
        }
        return new Character(characterName, classType, 0, user, characterAspect, characterStats.get("hp"), characterStats.get("atk"), characterStats.get("def"), characterStats.get("reiatsu"), 1);
    }

    public HashMap<String, Integer> setCharacterStats(int hp, int atk, int def, int reiatsu) {
        HashMap<String, Integer> characterStats = new HashMap<>();
        characterStats.put("hp", hp);
        characterStats.put("atk", atk);
        characterStats.put("def", def);
        characterStats.put("reiatsu", reiatsu);
        return characterStats;
    }
}
