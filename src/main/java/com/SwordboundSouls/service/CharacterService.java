package com.SwordboundSouls.service;

import java.util.ArrayList;
import java.util.HashMap;
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

	//TODO: Improve this
	public Character setCharacterAttributes(String characterName, String classType, User user) {
		HashMap<String, Integer> characterStats = new HashMap<>();
		String characterAspect = "ImageHere";
		switch (classType) {
			case "berserker":
				characterStats = setCharacterStats(20, 15, 15, 5);
				characterAspect = "https://i.imgur.com/pbwUTSW.png";
				break;
			case "balanced":
				characterStats = setCharacterStats(15, 10, 10, 10);
				characterAspect = "https://i.imgur.com/8noIjw3.jpg";
				break;
			case "spiritual":
				characterStats = setCharacterStats(10, 5, 5, 20);
				characterAspect = "https://i.imgur.com/S83m0qD.png";
				break;
		}
		return new Character(characterName, classType, 0, user, characterAspect, characterStats.get("hp"), characterStats.get("atk"), characterStats.get("def"), characterStats.get("reiatsu"), 1);
	}

	//TODO: Improve this
	public HashMap<String, Integer> setCharacterStats(int hp, int atk, int def, int reiatsu) {
		HashMap<String, Integer> characterStats = new HashMap<>();
		characterStats.put("hp", hp);
		characterStats.put("atk", atk);
		characterStats.put("def", def);
		characterStats.put("reiatsu", reiatsu);
		return characterStats;
	}
}
