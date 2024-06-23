package com.SwordboundSouls.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SwordboundSouls.entity.Hollows;
import com.SwordboundSouls.repository.HollowRepository;
@Service
public class HollowService {
	@Autowired
	private HollowRepository hRepo;
	
	public Hollows getHollow(String hollowName) {
		return hRepo.getHollow(hollowName);
	}
	public List<Hollows> getAllHollows(){
		return hRepo.findAll();
	}
}
