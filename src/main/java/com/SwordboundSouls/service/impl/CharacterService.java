package com.SwordboundSouls.service.impl;

import java.util.List;

import com.SwordboundSouls.entity.*;
import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.service.interfaces.ICharacterService;
import com.SwordboundSouls.utils.characterclasses.Berserker;
import com.SwordboundSouls.utils.characterclasses.Equilibrium;
import com.SwordboundSouls.utils.characterclasses.Spiritual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.repository.ICharacterRepository;

@Service
public class CharacterService implements ICharacterService {
	@Autowired
	private ICharacterRepository pRepo;

	@Autowired
	private PhysicalSkillService physicalSkillService;

	@Autowired
	private KidoSkillService kidoSkillService;

	public void determineCharacterStatsAfterBuffing(String buff) {

	}

	public int determineCharacterDamage(String attackAction, Character character) {
		int dmg = determinePhysicalDmg(attackAction, character);

		if(dmg == -1)
			dmg = determineReiatsuDmg(attackAction, character);

		return dmg;
	}

	public int determinePhysicalDmg(String attackAction, Character character) {
		int dmg = -1;
		PhysicalSkill physicalSkill = physicalSkillService.getByName(attackAction);

		if(physicalSkill != null)
			dmg = physicalSkillService.calculateDamage(character.getAtk(), character.getReiatsu(), physicalSkill.getModifier());

		return dmg;
	}

	public int determineReiatsuDmg(String attackAction, Character character) {
		int dmg = -1;
		KidoSkill kidoSkill = kidoSkillService.getByName(attackAction);

		if(kidoSkill != null)
			dmg = kidoSkillService.calculateDamage(character.getReiatsu(), kidoSkill.getModifier());

		return dmg;
	}

	public void createNewCharacter(Character pE) {
		pE.getPhysicalSkills().add(physicalSkillService.getById(1));
		pE.getKidoSkills().add(kidoSkillService.getById(1));
		pRepo.save(pE);
	}

	public void updateCharacter(Character character){
		pRepo.save(character);
	}
	
	public Character getCharacterByName(String characterName) {
		return pRepo.findByCharacterName(characterName).orElse(null);
	}

	public Character getCharacterByUsername(User user) {
		return pRepo.findByUser(user).orElse(null);
	}

	public List<Character> getAllCharacters(){
		return pRepo.findAll();
	}

	public void incrementXpAndCheckLevelUp(Character userCharacter, Hollow fightServiceHollow){
		userCharacter.incrementXp(fightServiceHollow.getXp());
		if(userCharacter.getXp() >= userCharacter.getXpLimit()){
			userCharacter.setXp(0);
			userCharacter.setLvl(userCharacter.getLvl() + 1);
			userCharacter.setXpLimit(userCharacter.calculateXpLimit());
			incrementAllCharacterStats(userCharacter);
		}
		updateCharacter(userCharacter);
	};

	public Character setCharacterAttributes(String characterName, String classType, User user) {
		Character character = null;
		switch (classType) {
			case Berserker.CLASS_TYPE:
				character = Berserker.buildCharacter(characterName, user);
				break;
			case Equilibrium.CLASS_TYPE:
				character = Equilibrium.buildCharacter(characterName, user);
				break;
			case Spiritual.CLASS_TYPE:
				character = Spiritual.buildCharacter(characterName, user);
				break;
		}
		return character;
	}

	public void incrementAllCharacterStats(Character character){
		switch(character.getClassType()){
			case Berserker.CLASS_TYPE:
				Berserker.incrementStats(character);
				break;
			case Equilibrium.CLASS_TYPE:
				Equilibrium.incrementStats(character);
				break;
			case Spiritual.CLASS_TYPE:
				Spiritual.incrementStats(character);
				break;
		}
	}
}
