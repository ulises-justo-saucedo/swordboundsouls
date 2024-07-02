package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.Hollow;
import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.helpers.ViewHelper;
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

    @GetMapping("/hunthollows")
    public ModelAndView huntHollows(){
        ModelAndView modelAndView = new ModelAndView(ViewHelper.HUNT_HOLLOWS);

        modelAndView.addObject("hollows", hollowService.getAllHollows());

        return modelAndView;
    }

    @GetMapping("/preparinghollowhunt")
    public ModelAndView preparingHollowHunt(@RequestParam("idHollow") int idHollow) {
        ModelAndView modelAndView = new ModelAndView(ViewHelper.PRE_HOLLOW_HUNT);
        User user = userService.getCurrentAuthenticatedUser();

        modelAndView.addObject("user", user);
        modelAndView.addObject("character", user.getCharacter());
        modelAndView.addObject("hollow", hollowService.getHollow(idHollow));

        return modelAndView;
    }

    @GetMapping("/hunt")
    public ModelAndView hunt(@RequestParam("idHollow") int idHollow) {
        ModelAndView modelAndView = new ModelAndView(ViewHelper.HUNTING_HOLLOW);
        User user = userService.getCurrentAuthenticatedUser();
        Hollow hollow = hollowService.getHollow(idHollow);

        modelAndView.addObject("user", user);
        modelAndView.addObject("character", user.getCharacter());
        modelAndView.addObject("hollow", hollow);

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
