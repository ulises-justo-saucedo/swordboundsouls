package com.SwordboundSouls.controller;

import com.SwordboundSouls.entity.Hollow;
import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.helpers.ViewHelper;
import com.SwordboundSouls.service.CharacterService;
import com.SwordboundSouls.service.FightService;
import com.SwordboundSouls.service.HollowService;
import com.SwordboundSouls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HollowController {
    @Autowired
    private UserService userService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private HollowService hollowService;
    @Autowired
    private FightService fightService;

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

        fightService.setUpEntities(user.getCharacter(), hollow);

        modelAndView.addObject("user", user);
        modelAndView.addObject("character", user.getCharacter());
        modelAndView.addObject("hollow", hollow);

        return modelAndView;
    }

    @PostMapping("/hunting")
    public ModelAndView hunting(@RequestParam(value = "attackAction", required = false) String attackAction, @RequestParam(value = "buffAction", required = false) String buffAction) {
        fightService.performTurn(buffAction, attackAction);
        User user = userService.getCurrentAuthenticatedUser();

        if(fightService.characterWon()){
            characterService.incrementXpAndCheckLevelUp(user.getCharacter(), fightService.getHollow());
            fightService.cleanEntities();
            return new ModelAndView("redirect:/home");
        }

        if(fightService.hollowWon()){
            fightService.cleanEntities();
            return new ModelAndView("redirect:/home");
        }

        ModelAndView modelAndView = new ModelAndView(ViewHelper.HUNTING_HOLLOW);

        modelAndView.addObject("user", user);
        modelAndView.addObject("character", fightService.getCharacter());
        modelAndView.addObject("hollow", fightService.getHollow());
        
        return modelAndView;
    }
}
