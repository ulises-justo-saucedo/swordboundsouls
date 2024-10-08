package com.SwordboundSouls.service.impl;

import com.SwordboundSouls.service.interfaces.IFightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.Hollow;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class FightService implements IFightService {
    @Autowired
    private CharacterService characterService;

    private Character character;
    private Hollow hollow;

    public void setUpEntities(Character character, Hollow hollow){
        setCharacter(character);
        setHollow(hollow);
    }

    public void cleanEntities(){
        setCharacter(null);
        setHollow(null);
    }

    public void performTurn(String buff, String attack){
        buffCharacter(buff);
        reduceHollowHp(attack);
        reduceCharacterHp();
    }

    public void buffCharacter(String buff){
        if(buff != null)
            characterService.determineCharacterStatsAfterBuffing(buff);
    }

    public void reduceCharacterHp(){
        character.reduceHp(hollow.getAtk());
    }

    public void reduceHollowHp(String attack){
        hollow.reduceHp(characterService.determineCharacterDamage(attack, character));
    }

    public boolean characterWon(){
        return hollow.isDead();
    }
    
    public boolean hollowWon(){
        return character.isDead();
    }
}
