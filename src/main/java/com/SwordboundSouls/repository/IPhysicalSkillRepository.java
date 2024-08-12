package com.SwordboundSouls.repository;

import com.SwordboundSouls.entity.PhysicalSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IPhysicalSkillRepository extends JpaRepository<PhysicalSkill, Serializable> {
    public PhysicalSkill findByName(String name);
}
