package com.SwordboundSouls.service.impl;
import java.util.List;

import com.SwordboundSouls.service.interfaces.IHollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SwordboundSouls.entity.Hollow;
import com.SwordboundSouls.repository.IHollowRepository;
@Service
public class HollowService implements IHollowService {
	@Autowired
	private IHollowRepository hRepo;
	
	public Hollow getHollow(String hollowName) {
		return hRepo.findByHollowName(hollowName).orElse(null);
	}
	public Hollow getHollow(int idHollow){
		return hRepo.findById(idHollow).orElse(null);
	}
	public List<Hollow> getAllHollows(){
		return hRepo.findAll();
	}
}
