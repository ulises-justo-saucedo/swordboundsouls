package com.SwordboundSouls.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.SwordboundSouls.entity.Characters;
@Repository
public interface CharacterRepository extends JpaRepository<Characters,String>{
	public static final JdbcTemplate databaseTemplate = new JdbcTemplate();
	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM characters WHERE username = :username) THEN 1 ELSE 0 END", nativeQuery = true)
	public Integer verifyIfUserHasCharacter(@Param("username") String username);
	
	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM characters WHERE character_name = :characterName) THEN 1 ELSE 0 END", nativeQuery = true)
	public Integer verifyIfCharacterExists(@Param("characterName") String characterName);

	@Query(value="SELECT * FROM characters WHERE character_name = :characterName",nativeQuery=true)
	public Characters getCharacterByName(@Param("characterName") String characterName);
	
	@Query(value="SELECT * FROM characters WHERE username = :username",nativeQuery=true)
	public Characters getCharacterByUsername(@Param("username") String username);
}
