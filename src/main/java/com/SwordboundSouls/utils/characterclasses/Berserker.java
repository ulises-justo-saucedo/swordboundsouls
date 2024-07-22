package com.SwordboundSouls.utils.characterclasses;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.User;

public class Berserker {
    public static final String CLASS_TYPE = "Berserker";
    public static final String BASE_ASPECT = "https://i.imgur.com/pbwUTSW.png";
    public static final int BASE_HP = 20;
    public static final int BASE_ATK = 15;
    public static final int BASE_DEF = 15;
    public static final int BASE_REIATSU = 5;
    public static final int BASE_LEVEL = 1;
    public static final int BASE_XP = 0;
    public static final float STANDARD_MULTIPLIER = 0.5f;
    public static final float SPECIAL_MULTIPLIER = 1.7f;

    public static Character buildCharacter(String characterName, User user){
        return new Character(characterName, CLASS_TYPE, BASE_XP, user, BASE_ASPECT, BASE_HP, BASE_ATK, BASE_DEF, BASE_REIATSU, BASE_LEVEL);
    }

    public static void incrementStats(Character character){
        character.setHp(specialStatFormula(character.getHp()));
        character.setAtk(standardStatFormula(character.getAtk()));
        character.setDef(standardStatFormula(character.getDef()));
        character.setReiatsu(standardStatFormula(character.getReiatsu()));
    }

    private static int standardStatFormula(int parameter){
        return (int)(parameter + Math.sqrt(STANDARD_MULTIPLIER + parameter));
    }

    private static int specialStatFormula(int parameter){
        return (int)(parameter + Math.sqrt(STANDARD_MULTIPLIER + SPECIAL_MULTIPLIER + parameter));
    }
}
