package com.SwordboundSouls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.CharacterFighting;
import com.SwordboundSouls.entity.HollowFighting;
import com.SwordboundSouls.repository.HollowFightingRepository;

@Service
public class HollowFightingService {
	@Autowired
	private HollowFightingRepository hfRepo;
	
	public void createNewHollowFighting(HollowFighting hf) {
		hfRepo.save(hf);
	}
	public HollowFighting getHollowFighting(String hollowName) {
		return hfRepo.getHollowFighting(hollowName);
	}
	public void deleteHollowFighting(String hollowName) {
		hfRepo.deleteById(hollowName);
	}
}
