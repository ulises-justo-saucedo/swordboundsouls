package com.SwordboundSouls.service;

import java.util.ArrayList;
import java.util.List;

import com.SwordboundSouls.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.Character;
import com.SwordboundSouls.repository.CharacterRepository;

@Service
public class CharacterService {
	@Autowired
	private CharacterRepository pRepo;

	public void createNewCharacter(Character pE) {
		pRepo.save(setBasicSkillsForEveryCharacter(pE));
	}

	public Character setBasicSkillsForEveryCharacter(Character pE){
		//pE.getPhysicalSkills().add("Zanpaku-tō");
		//pE.getKidoSkills().add("Shō");
		return pE;
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
}
