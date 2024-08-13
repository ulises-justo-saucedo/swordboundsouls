package com.SwordboundSouls.service.interfaces;

import com.SwordboundSouls.entity.*;
import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.utils.characterclasses.Berserker;
import com.SwordboundSouls.utils.characterclasses.Equilibrium;
import com.SwordboundSouls.utils.characterclasses.Spiritual;

import java.util.List;

public interface ICharacterService {
    public void determineCharacterStatsAfterBuffing(String buff);

    public int determineCharacterDamage(String attackAction, Character character);

    public int determinePhysicalDmg(String attackAction, Character character);

    public int determineReiatsuDmg(String attackAction, Character character);

    public void createNewCharacter(Character pE);

    public void updateCharacter(Character character);

    public Character getCharacterByName(String characterName);

    public Character getCharacterByUsername(User user);

    public List<Character> getAllCharacters();

    public void incrementXpAndCheckLevelUp(Character userCharacter, Hollow fightServiceHollow);

    public Character setCharacterAttributes(String characterName, String classType, User user);

    public void incrementAllCharacterStats(Character character);
}
