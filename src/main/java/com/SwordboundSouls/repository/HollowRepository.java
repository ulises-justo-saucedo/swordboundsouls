package com.SwordboundSouls.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SwordboundSouls.entity.Hollows;
@Repository
public interface HollowRepository extends JpaRepository<Hollows,String> {
	@Query(value="SELECT * FROM hollows WHERE hollow_name = :hollowName",nativeQuery=true)
	public Hollows getHollow(@Param("hollowName") String hollowName);
}
