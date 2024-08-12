package com.SwordboundSouls.service;

import com.SwordboundSouls.entity.PhysicalSkill;
import com.SwordboundSouls.repository.IPhysicalSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicalSkillService {
    @Autowired
    private IPhysicalSkillRepository physicalSkillRepository;

    public int calculateDamage(int characterAtk, int characterReiatsu, int modifier) {
        return (int) (characterAtk + ((characterAtk + modifier) * (characterReiatsu / 10)));
    }

    public PhysicalSkill getById(int id){
        return physicalSkillRepository.findById(id).orElse(null);
    }

    public PhysicalSkill getByName(String name) {
        return physicalSkillRepository.findByName(name);
    }

    public void save(PhysicalSkill physicalSkill){
        physicalSkillRepository.save(physicalSkill);
    }


}
