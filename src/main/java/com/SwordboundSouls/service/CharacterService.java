package com.SwordboundSouls.service;

import java.util.List;

import com.SwordboundSouls.entity.Hollow;
import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.utils.characterclasses.Berserker;
import com.SwordboundSouls.utils.characterclasses.Equilibrium;
import com.SwordboundSouls.utils.characterclasses.Spiritual;
import com.SwordboundSouls.utils.skills.character.KidoSkills;
import com.SwordboundSouls.utils.skills.character.PhysicalSkills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.repository.CharacterRepository;

@Service
public class CharacterService {
	@Autowired
	private CharacterRepository pRepo;

	public void createNewCharacter(Character pE) {
		pE.getPhysicalSkills().add(PhysicalSkills.BASIC_SKILL.getName());
		pE.getKidoSkills().add(KidoSkills.BASIC_SKILL.getName());
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
