package com.SwordboundSouls.utils.characterclasses;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.entity.User;

public class Spiritual {
    public static final String CLASS_TYPE = "Spiritual";
    public static final String BASE_ASPECT = "https://i.imgur.com/S83m0qD.png";
    public static final int BASE_HP = 10;
    public static final int BASE_ATK = 5;
    public static final int BASE_DEF = 5;
    public static final int BASE_REIATSU = 20;
    public static final int BASE_LEVEL = 1;
    public static final int BASE_XP = 0;
    public static final float STANDARD_MULTIPLIER = 0.3f;
    public static final float SPECIAL_MULTIPLIER = 1.8f;

    public static Character buildCharacter(String characterName, User user){
        return new Character(characterName, CLASS_TYPE, BASE_XP, user, BASE_ASPECT, BASE_HP, BASE_ATK, BASE_DEF, BASE_REIATSU, BASE_LEVEL);
    }

    public static void incrementStats(Character character){
        character.setHp(standardStatFormula(character.getHp()));
        character.setAtk(standardStatFormula(character.getAtk()));
        character.setDef(standardStatFormula(character.getDef()));
        character.setReiatsu(specialStatFormula(character.getReiatsu()));
    }

    private static int standardStatFormula(int parameter){
        return (int)(parameter + Math.sqrt(STANDARD_MULTIPLIER + parameter));
    }

    private static int specialStatFormula(int parameter){
        return (int)(parameter + Math.sqrt(STANDARD_MULTIPLIER + SPECIAL_MULTIPLIER + parameter));
    }
}
