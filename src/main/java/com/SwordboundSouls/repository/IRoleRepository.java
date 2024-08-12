package com.SwordboundSouls.repository;

import com.SwordboundSouls.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Serializable> {
    Optional<Role> findByName(String name);
}
