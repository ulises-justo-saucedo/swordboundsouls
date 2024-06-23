package com.SwordboundSouls.repository;
import com.SwordboundSouls.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.SwordboundSouls.entity.Character;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Serializable>{
	public Optional<Character> findByUser(User user);

	public Optional<Character> findByCharacterName(String characterName);
}
