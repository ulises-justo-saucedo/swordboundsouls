package com.SwordboundSouls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SwordboundSouls.entity.CharacterFighting;
import com.SwordboundSouls.entity.HollowFighting;

@Repository
public interface HollowFightingRepository extends JpaRepository<HollowFighting,String> {
	@Query(value="SELECT * FROM hollow_fighting WHERE hollow_name = :hollowName",nativeQuery=true)
	public HollowFighting getHollowFighting(@Param("hollowName") String hollowName);

}
