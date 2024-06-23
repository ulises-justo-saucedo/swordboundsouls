package com.SwordboundSouls.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SwordboundSouls.entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users,String>{
	@Query(value="SELECT * FROM users WHERE username = :username",nativeQuery=true)
	public Users getUser(@Param("username") String username);
	
	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM users WHERE username = :username AND pass = :pass) THEN 1 ELSE 0 END", nativeQuery = true)
	public Integer verifyIfUserExists(@Param("username") String username,@Param("pass") String pass);
}
