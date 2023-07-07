package tn.camps.tuncamps.persistence.repository.parc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.parc.Activity;

import java.util.Date;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query("SELECT COALESCE(SUM(a.minParticipants), 0) FROM Activity a WHERE a.parc.idParc = :parcId AND a.startDate >= :startDate AND a.endDate <= :endDate")
    int sumMinParticipantsByParcAndTime(@Param("parcId") int parcId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


}