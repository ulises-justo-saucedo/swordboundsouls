package com.SwordboundSouls.service.interfaces;

import com.SwordboundSouls.entity.PhysicalSkill;

public interface IPhysicalSkillService {
    public int calculateDamage(int characterAtk, int characterReiatsu, int modifier);

    public PhysicalSkill getById(int id);

    public PhysicalSkill getByName(String name);

    public void save(PhysicalSkill physicalSkill);
}
