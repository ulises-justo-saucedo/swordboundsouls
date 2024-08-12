package com.SwordboundSouls.repository;

import com.SwordboundSouls.entity.BuffSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IBuffSkillRepository extends JpaRepository<BuffSkill, Serializable> {

}
