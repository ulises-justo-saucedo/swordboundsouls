package com.SwordboundSouls.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SwordboundSouls.entity.Hollow;
@Repository
public interface HollowRepository extends JpaRepository<Hollow,String> {
	@Query(value="SELECT * FROM hollows WHERE hollow_name = :hollowName",nativeQuery=true)
	public Hollow getHollow(@Param("hollowName") String hollowName);
}
