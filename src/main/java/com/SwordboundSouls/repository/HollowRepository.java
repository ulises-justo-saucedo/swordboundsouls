package com.SwordboundSouls.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SwordboundSouls.entity.Hollow;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface HollowRepository extends JpaRepository<Hollow, Serializable> {
	public Optional<Hollow> findByHollowName(String hollowName);
}
