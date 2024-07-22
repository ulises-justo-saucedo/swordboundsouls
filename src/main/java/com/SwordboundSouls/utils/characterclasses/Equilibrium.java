package com.SwordboundSouls.utils.characterclasses;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.User;

public class Equilibrium {
    public static final String CLASS_TYPE = "Equilibrium";
    public static final String BASE_ASPECT = "https://i.imgur.com/8noIjw3.jpg";
    public static final int BASE_HP = 15;
    public static final int BASE_ATK = 10;
    public static final int BASE_DEF = 10;
    public static final int BASE_REIATSU = 10;
    public static final int BASE_LEVEL = 1;
    public static final int BASE_XP = 0;
    public static final float STANDARD_MULTIPLIER = 1.2f;

    public static Character buildCharacter(String characterName, User user){
        return new Character(characterName, CLASS_TYPE, BASE_XP, user, BASE_ASPECT, BASE_HP, BASE_ATK, BASE_DEF, BASE_REIATSU, BASE_LEVEL);
    }

    public static void incrementStats(Character character){
        character.setHp(statFormula(character.getHp()));
        character.setAtk(statFormula(character.getAtk()));
        character.setDef(statFormula(character.getDef()));
        character.setReiatsu(statFormula(character.getReiatsu()));
    }

    private static int statFormula(int parameter){
        return (int)(parameter + Math.sqrt(STANDARD_MULTIPLIER + parameter));
    }
}
