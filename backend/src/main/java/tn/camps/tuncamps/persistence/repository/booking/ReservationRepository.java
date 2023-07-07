package tn.camps.tuncamps.persistence.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.booking.Reservation;
import tn.camps.tuncamps.persistence.entity.booking.ReservationStatus;
import tn.camps.tuncamps.persistence.entity.booking.Sale;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.user.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.status = :status")
    List<Reservation> findByStatus(@Param("status") ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :user_id")
    List<Reservation> findByUser(@Param("user") User user);

    @Query("SELECT r FROM Reservation r WHERE r.parc.idParc = :parc_id")
    List<Reservation> findByParc(@Param("parc") Parc parc);

    @Query("SELECT r FROM Reservation r WHERE r.sale.id = :sale_id")
    List<Reservation> findBySale(@Param("sale") Sale sale);

    @Query(nativeQuery = true,value ="SELECT r FROM reservation r WHERE r.startDate >=?1 AND r.endDate <=?2")
    List<Reservation> findByDateBetween(@Param("start_Date") Date debut, @Param("end_Date") Date fin);

    @Query(nativeQuery = true,value ="SELECT r.* FROM reservation r RIGHT JOIN parc p on r.parc_id= p.id_parc WHERE p.name LIKE %:parc%")
    List<Reservation> findByParcName(@Param("parc") String parcName);

    @Query(nativeQuery = true,value ="SELECT * FROM reservation r RIGHT JOIN sale s on s.id=r.sale_id WHERE s.start_date <=?1 AND s.expiration_date >=?1")
    List<Reservation> findBySaleDate(LocalDate dateR);

    @Query(nativeQuery = true,value ="SELECT * FROM Reservation  WHERE sale_id IS NOT NULL ")
    List<Reservation> findBySaleFound();


    @Query("SELECT sum(r.personnbr) FROM Reservation r where r.parc.idParc=:parc")
    int findNumberPersonParc(@Param("parc") int parc_id);

    @Query("SELECT sum(r.personnbr) FROM Reservation r where r.activity.id=:activity")
    int findNumberPersonActivity(@Param("activity") int activity_id);




}
