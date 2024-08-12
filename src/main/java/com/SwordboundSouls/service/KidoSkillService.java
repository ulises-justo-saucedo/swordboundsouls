package com.SwordboundSouls.service;

import com.SwordboundSouls.entity.KidoSkill;
import com.SwordboundSouls.repository.IKidoSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidoSkillService {
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
