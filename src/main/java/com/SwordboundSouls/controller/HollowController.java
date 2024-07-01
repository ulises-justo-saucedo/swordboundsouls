package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.CharacterFighting;
import com.SwordboundSouls.entity.HollowFighting;
import com.SwordboundSouls.service.CharacterService;
import com.SwordboundSouls.service.HollowService;
import com.SwordboundSouls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HollowController {
    @Autowired
    private UserService userService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private HollowService hollowService;

    @GetMapping("/huntHollows")
    public ModelAndView huntHollows(){
        ModelAndView modelAndView = new ModelAndView("huntHollows");
        modelAndView.addObject("user", null); // Pass the user here through Spring Security
        modelAndView.addObject("hollows", hollowService.getAllHollows());
        return modelAndView;
    }

    @GetMapping("/preparingHollowHunt")
    public ModelAndView preparingHollowHunt(@RequestParam("hollowName") String hollowName, @RequestParam("username") String username, @RequestParam("characterName") String characterName) {
        ModelAndView modelAndView = new ModelAndView("preparingHollowHunt");
        modelAndView.addObject("user", null); // Pass the user here through Spring Security
        modelAndView.addObject("hollow", hollowService.getHollow(hollowName));
        return modelAndView;
    }

    @GetMapping("/hunt")
    public ModelAndView hunt(@RequestParam("buttonPressed") String buttonPressed, @RequestParam("username") String username, @RequestParam("characterName") String characterName, @RequestParam("hollowName") String hollowName) {
        ModelAndView modelAndView = new ModelAndView();
        if (buttonPressed.equals("confirm")) {
            //Set things up for the combat
            CharacterFighting cf = (CharacterFighting) characterService.getCharacterByName(characterName);
            HollowFighting hf = (HollowFighting) hollowService.getHollow(hollowName);
            //createNewCharacterFighting(cf);
            //createNewHollowFighting(hf);
            modelAndView.addObject("user", null); //Pass the user here through Spring Security
            modelAndView.addObject("character", cf);
            modelAndView.addObject("hollow", hf);
            modelAndView.setViewName("huntingHollow");
        } else {
            modelAndView.setViewName("huntHollows");
            modelAndView.addObject("user", null); // Pass the user here through Spring Security
            modelAndView.addObject("hollows", hollowService.getAllHollows());
        }
        return modelAndView;
    }
/*
    @GetMapping("/manageCombatAgainstHollow")
    public ModelAndView manageCombatAgainstHollow(@RequestParam("username") String username, @RequestParam("characterName") String characterName, @RequestParam("hollowName") String hollowName, @RequestParam("attackAction") String attackAction, @RequestParam(value = "buffAction", required = false) String buffAction) {
        User user = userService.getUser(username);
        //CharacterFighting characterFighting = getCharacterFighting(characterName);
        //HollowFighting hollowFighting = getHollowFighting(hollowName);
        //manageCharacterBuffs(buffAction, characterFighting);
        //manageCharacterAndHollowDamage(hollowFighting, characterFighting, attackAction);
        //return checkIfCharacterOrHollowAreDead(hollowFighting, characterFighting, user);
        return null;
    }

    public void manageCharacterBuffs(String buffAction, CharacterFighting character) {
        if (buffAction != null) {
            character.determineCharacterStatsAfterBuffing(buffAction);
        }
    }

    public void manageCharacterAndHollowDamage(HollowFighting hollow, CharacterFighting character, String attackAction) {
        hollow.reduceHp(character.determineCharacterDamage(attackAction));
        character.reduceHp(hollow.getAtk());
    }

    public ModelAndView checkIfCharacterOrHollowAreDead(HollowFighting hollow, CharacterFighting character, User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (hollow.isDead()) {
            //TODO: Increment character's xp and check if levels up. If he levels up then increment overall stats
            //TODO: Also, balance Hollow's and character's dmg formula :]
            deleteFightingEntities(hollow.getHollowName(), character.getCharacterName());
            modelAndView = passEntitiesToViewAndSetView(user, characterService.getCharacterByName(character.getCharacterName()), hollowService.getAllHollows(), "huntHollows");
        } else if (character.isDead()) {
            deleteFightingEntities(hollow.getHollowName(), character.getCharacterName());
            modelAndView = passEntitiesToViewAndSetView(user, characterService.getCharacterByName(character.getCharacterName()), "home");
        } else {
            //updateHollowFighting(hollow);
            //updateCharacterFighting(character);
            modelAndView = passEntitiesToViewAndSetView(user, character, hollow, "huntingHollow");
        }
        return modelAndView;
    }

    public void deleteFightingEntities(String hollowName, String characterName) {
        //deleteHollowFighting(hollowName);
        //deleteCharacterFighting(characterName);
    }
*/
}
