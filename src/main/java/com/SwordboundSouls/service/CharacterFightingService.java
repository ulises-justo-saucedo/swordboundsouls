package com.SwordboundSouls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.CharacterFighting;
import com.SwordboundSouls.repository.CharacterFightingRepository;

@Service
public class CharacterFightingService {
	@Autowired
	private CharacterFightingRepository cfRepo;
	
	public void createNewCharacterFighting(CharacterFighting cf) {
		cfRepo.save(cf);
	}
	public CharacterFighting getCharacterFighting(String characterName) {
		CharacterFighting cf = cfRepo.getCharacterFighting(characterName);
		cf.setPhysicalSkills(cfRepo.getPhysicalSkillsOfCharacterFighting(characterName));
		cf.setKidoSkills(cfRepo.getKidoSkillsOfCharacterFighting(characterName));
		cf.setBuffs(cfRepo.getBuffsOfCharacterFighting(characterName));
		return cf;
	}
	public void deleteCharacterFighting(String characterName) {
		cfRepo.deleteById(characterName);
	}
}
