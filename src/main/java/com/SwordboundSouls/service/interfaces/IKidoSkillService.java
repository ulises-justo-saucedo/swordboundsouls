package com.SwordboundSouls.service.interfaces;

import com.SwordboundSouls.entity.KidoSkill;

public interface IKidoSkillService {
    public int calculateDamage(int characterReiatsu, int modifier);

    public KidoSkill getById(int id);

    public KidoSkill getByName(String name);
}
