package com.SwordboundSouls.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SwordboundSouls.entity.Hollow;
import com.SwordboundSouls.repository.HollowRepository;
@Service
public class HollowService {
	@Autowired
	private HollowRepository hRepo;
	
	public Hollow getHollow(String hollowName) {
		return hRepo.getHollow(hollowName);
	}
	public List<Hollow> getAllHollows(){
		return hRepo.findAll();
	}
}
