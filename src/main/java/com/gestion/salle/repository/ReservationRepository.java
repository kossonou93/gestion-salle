package com.gestion.salle.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.salle.model.Ordinateur;
import com.gestion.salle.model.Reservation;
import com.gestion.salle.model.Salle;
import com.gestion.salle.model.VideoProjecteur;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	//Stream<Reservation> findByFinAfter(Date fin);
	@Query(value="SELECT * FROM reservations WHERE salle_id=:idSalle AND (:dateDebut AND :dateFin BETWEEN debut AND fin)",nativeQuery=true)
	List<?> CheckSallAvailability(Long idSalle, Date dateDebut, Date dateFin);
	///*
	@Query(value="SELECT * FROM reservations WHERE salle_id=:idSalle AND (:dateDebut AND :dateFin BETWEEN debut AND fin)",nativeQuery=true)
	List<?> CheckSall(Long idSalle,Date dateDebut, Date dateFin);
	
	@Query(value="SELECT * FROM reservations WHERE ordinateur_id=:idOrdinateur AND (:dateDebut AND :dateFin BETWEEN debut AND fin)",nativeQuery=true)
	List<?> CheckOrdinateur(Long idOrdinateur,Date dateDebut, Date dateFin);
	
	@Query(value="SELECT * FROM reservations WHERE ordinateur_id=:idOrdinateur AND (:dateDebut AND :dateFin BETWEEN debut AND fin)",nativeQuery=true)
	List<?> CheckOrdiAvailability(Long idOrdinateur,Date dateDebut, Date dateFin);
	
	@Query(value="SELECT * FROM reservations WHERE video_projecteur_id=:idVideoProjecteur AND (:dateDebut AND :dateFin BETWEEN debut AND fin)",nativeQuery=true)
	List<?> CheckVideoProjecteur(Long idVideoProjecteur,Date dateDebut, Date dateFin);
	
	@Query(value="SELECT * FROM reservations WHERE video_projecteur_id=:idVideoProjecteur AND (:dateDebut AND :dateFin BETWEEN debut AND fin)",nativeQuery=true)
	List<?> CheckVideoAvailability(Long idVideoProjecteur,Date dateDebut, Date dateFin);
	
	Optional<Reservation> findByFin(Date fin);
	Optional<Reservation> findBySalle(Salle salle);
	List<Reservation> findAllBySalle(Salle salle);
	List<Reservation> findAll();
	List<Reservation> findAllByOrdinateur(Ordinateur ordinateur);
	List<Reservation> findAllByVideoProjecteur(VideoProjecteur videoProjecteur);
}
