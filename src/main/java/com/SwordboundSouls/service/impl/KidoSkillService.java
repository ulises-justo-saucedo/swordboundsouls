package com.SwordboundSouls.service.impl;

import com.SwordboundSouls.entity.KidoSkill;
import com.SwordboundSouls.repository.IKidoSkillRepository;
import com.SwordboundSouls.service.interfaces.IKidoSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidoSkillService implements IKidoSkillService {
    @Autowired
    private IKidoSkillRepository kidoSkillRepository;

    public int calculateDamage(int characterReiatsu, int modifier) {
        return (int) (characterReiatsu + ((characterReiatsu + modifier) * (characterReiatsu / 5)));
    }

    public KidoSkill getById(int id){
        return kidoSkillRepository.findById(id).orElse(null);
    }

    public KidoSkill getByName(String name) {
        return kidoSkillRepository.findByName(name);
    }
}
