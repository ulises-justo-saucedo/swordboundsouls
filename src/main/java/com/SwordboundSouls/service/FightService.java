package com.SwordboundSouls.service;

import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.Hollow;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class FightService {
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

    private void buffCharacter(String buff){
        if(buff != null)
            character.determineCharacterStatsAfterBuffing(buff);
    }

    private void reduceCharacterHp(){
        character.reduceHp(hollow.getAtk());
    }

    private void reduceHollowHp(String attack){
        hollow.reduceHp(character.determineCharacterDamage(attack));
    }

    public boolean characterWon(){
        return hollow.isDead();
    }
    
    public boolean hollowWon(){
        return character.isDead();
    }
}
