package tn.camps.tuncamps.persistence.repositories.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.camps.tuncamps.persistence.entities.booking.Reservation;
import tn.camps.tuncamps.persistence.entities.booking.Sale;
import tn.camps.tuncamps.persistence.entities.parc.Parc;
import tn.camps.tuncamps.persistence.entities.user.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.status = :status")
    List<Reservation> findByStatus(@Param("status") String status);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :user_id")
    List<Reservation> findByUser(@Param("user") User user);

    @Query("SELECT r FROM Reservation r WHERE r.parc.idParc = :parc_id")
    List<Reservation> findByParc(@Param("parc") Parc parc);

    @Query("SELECT r FROM Reservation r WHERE r.sale.id = :sale_id")
    List<Reservation> findBySale(@Param("sale") Sale sale);

    @Query(nativeQuery = true,value ="SELECT r FROM Reservation r WHERE r.startDate >=?1 AND r.endDate <=?2")
    List<Reservation> findByDateBetween(@Param("start_Date") Date debut, @Param("end_Date") Date fin);

    @Query("SELECT r FROM Reservation r RIGHT JOIN r.parc p WHERE p.name LIKE %:parcName%")
    List<Reservation> findByParcName(@Param("parc") String parcName);

    @Query(nativeQuery = true,value ="SELECT * FROM Reservation r RIGHT JOIN sale s on s.id=r.sale_id WHERE s.startDate <=?1 AND s.expirationDate >=?1")
    List<Reservation> findBySaleDate(LocalDate dateR);

    //@Query("SELECT r FROM Reservation r WHERE r.sale.amount >= :prix order by r.")
    //List<Reservation> findBySaleAmount(@Param("amount") String prix);


}
