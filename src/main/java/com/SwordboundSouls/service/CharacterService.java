package com.SwordboundSouls.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.Characters;
import com.SwordboundSouls.repository.CharacterRepository;

@Service
public class CharacterService {
	@Autowired
	private CharacterRepository pRepo;
	
	public boolean verifyIfUserHasCharacter(String username) {
		return pRepo.verifyIfUserHasCharacter(username) == 1;
	}
	
	public boolean verifyIfCharacterExists(String characterName) {
		return pRepo.verifyIfCharacterExists(characterName) == 1;
	}
	
	public void createNewCharacter(Characters pE) {
		pRepo.save(setBasicSkillsForEveryCharacter(pE));
	}
	public Characters setBasicSkillsForEveryCharacter(Characters pE){
		pE.getPhysicalSkills().add("Zanpaku-tō");
		pE.getKidoSkills().add("Shō");
		return pE;
	}
	public Characters getCharacterByName(String characterName) {
		return pRepo.getCharacterByName(characterName);
	}
	
	public Characters getCharacterByUsername(String username) {
		return pRepo.getCharacterByUsername(username);
	}
	public List<Characters> getAllCharacters(){
		return pRepo.findAll();
	}
}
