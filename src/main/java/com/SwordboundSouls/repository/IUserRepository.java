package com.SwordboundSouls.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SwordboundSouls.entity.User;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Serializable>{
	public Optional<User> findByUsername(String username);
}
