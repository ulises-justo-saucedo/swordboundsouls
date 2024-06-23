package com.SwordboundSouls.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SwordboundSouls.entity.CharacterFighting;
@Repository
public interface CharacterFightingRepository extends JpaRepository<CharacterFighting,String> {
	@Query(value="SELECT * FROM character_fighting WHERE character_name = :characterName",nativeQuery=true)
	public CharacterFighting getCharacterFighting(@Param("characterName") String characterName);
	
	@Query(value="SELECT physical_skills FROM characters_physical_skills WHERE characters_character_name = :characterName",nativeQuery=true)
	public List<String> getPhysicalSkillsOfCharacterFighting(@Param("characterName") String characterName);
	
	@Query(value="SELECT kido_skills FROM characters_kido_skills WHERE characters_character_name = :characterName",nativeQuery=true)
	public List<String> getKidoSkillsOfCharacterFighting(@Param("characterName") String characterName);
	
	@Query(value="SELECT buffs FROM characters_buffs WHERE characters_character_name = :characterName",nativeQuery=true)
	public List<String> getBuffsOfCharacterFighting(@Param("characterName") String characterName);
	
}
