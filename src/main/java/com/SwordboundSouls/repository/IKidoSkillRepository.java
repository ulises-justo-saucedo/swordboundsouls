package com.SwordboundSouls.repository;

import com.SwordboundSouls.entity.KidoSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IKidoSkillRepository extends JpaRepository<KidoSkill, Serializable> {
    public KidoSkill findByName(String name);
}
