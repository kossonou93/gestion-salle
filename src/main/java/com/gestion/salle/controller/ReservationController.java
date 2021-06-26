package com.gestion.salle.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.salle.model.Enseignant;
import com.gestion.salle.model.Ordinateur;
import com.gestion.salle.model.Reservation;
import com.gestion.salle.model.Salle;
import com.gestion.salle.model.VideoProjecteur;
import com.gestion.salle.repository.EnseigantRepository;
import com.gestion.salle.repository.OrdinateurRepository;
import com.gestion.salle.repository.ReservationRepository;
import com.gestion.salle.repository.SalleRepository;
import com.gestion.salle.repository.VideoProjecteurRepository;
import com.gestion.salle.response.MessageResponse;
import com.gestion.salle.security.request.ReservationRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/reservation")
//@Api(value="User Management System", description="Operations pertaining to user in User Management System")
public class ReservationController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	SalleRepository salleRepository;
	
	@Autowired
	EnseigantRepository enseignantRepository;
	
	@Autowired
	VideoProjecteurRepository videoProjecteurRepository;
	
	@Autowired
	OrdinateurRepository ordinateurRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationRequest.class);
	
	
	@PostMapping("/getreservationsalle")
	public List<?> getReservationSalle(@Valid @RequestBody ReservationRequest reservationRequest) {
		Salle salle = salleRepository.findByName(reservationRequest.getSalle()).orElseThrow(() -> new ResourceNotFoundException("salle not found for this  :: " + reservationRequest.getSalle()));
		List<Reservation> res = reservationRepository.findAllBySalle(salle);
		System.out.println(res);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<?> reservation=new ArrayList();
		try {
			Date castedDebut=format.parse(reservationRequest.getDebut());
			Date castedFin=format.parse(reservationRequest.getFin());
			System.out.println(castedDebut);
			System.out.println(castedFin);
			 reservation= reservationRepository.CheckSall(salle.getId(), castedDebut, castedFin);
			 System.out.println(reservation);
		}catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		
		return reservation;
	}
	
	@PostMapping("/getreservationordinateur")
	public List<?> getReservationOrdinateur(@Valid @RequestBody ReservationRequest reservationRequest) {
		Ordinateur ordinateur = ordinateurRepository.findByName(reservationRequest.getOrdinateur()).orElseThrow(() -> new ResourceNotFoundException("ordinateur not found for this  :: " + reservationRequest.getOrdinateur()));
		List<Reservation> res = reservationRepository.findAllByOrdinateur(ordinateur);
		System.out.println(res);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<?> reservation=new ArrayList();
		try {
			Date castedDebut=format.parse(reservationRequest.getDebut());
			Date castedFin=format.parse(reservationRequest.getFin());
			System.out.println(castedDebut);
			System.out.println(castedFin);
			 reservation= reservationRepository.CheckOrdinateur(ordinateur.getId(), castedDebut, castedFin);
			 System.out.println(reservation);
		}catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		
		return reservation;
	}
	
	@PostMapping("/getreservationvideoprojecteur")
	public List<?> getReservationVideoProjecteur(@Valid @RequestBody ReservationRequest reservationRequest) {
		VideoProjecteur videoProjecteur = videoProjecteurRepository.findByName(reservationRequest.getVideoprojecteur()).orElseThrow(() -> new ResourceNotFoundException("video projector not found for this  :: " + reservationRequest.getVideoprojecteur()));
		List<Reservation> res = reservationRepository.findAllByVideoProjecteur(videoProjecteur);
		System.out.println(res);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<?> reservation=new ArrayList();
		try {
			Date castedDebut=format.parse(reservationRequest.getDebut());
			Date castedFin=format.parse(reservationRequest.getFin());
			System.out.println(castedDebut);
			System.out.println(castedFin);
			 reservation= reservationRepository.CheckVideoProjecteur(videoProjecteur.getId(), castedDebut, castedFin);
			 System.out.println(reservation);
		}catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		
		return reservation;
	}
	
	
	@PostMapping("/addreservation/salle")
	public Object addReservationSalle(@Valid @RequestBody ReservationRequest reservationRequest) {
		Salle salle = salleRepository.findByName(reservationRequest.getSalle()).orElseThrow(() -> new ResourceNotFoundException("Ordinateur not found for this i :: "));
		List<Reservation> res = reservationRepository.findAllBySalle(salle);
		//Long lastReservationId =  res.get(res.size()-1).getId();
		System.out.println(res);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<?> reservation=new ArrayList();
		try {
			Date castedDebut=format.parse(reservationRequest.getDebut());
			Date castedFin=format.parse(reservationRequest.getFin());
			System.out.println(castedDebut);
			System.out.println(castedFin);
			// System.out.println(lastReservationId);
			 
			 reservation= reservationRepository.CheckSallAvailability(salle.getId(), castedDebut, castedFin);
			 System.out.println(reservation);
			 
			 if(reservation.isEmpty()) {
				//il peut reserver
				Enseignant enseignant = enseignantRepository.findByEmail(reservationRequest.getEnseignant()).orElseThrow(() -> new ResourceNotFoundException("Enseignant not found for this email :: " + reservationRequest.getEnseignant()));
				
				Reservation reserv = new Reservation(castedDebut, castedFin, enseignant);
				reserv.setSalle(salle);
				reservationRepository.save(reserv);
				
				return reservation;
				} else {
					return reservation;
				}
		}catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		
		return reservation;
		
	}
	
	@PostMapping("/addreservation/ordinateur")
	public Object addReservationOrdinateur(@Valid @RequestBody ReservationRequest reservationRequest) {
		Ordinateur ordinateur = ordinateurRepository.findByName(reservationRequest.getOrdinateur()).orElseThrow(() -> new ResourceNotFoundException("Ordinateur not found for this :: " + reservationRequest.getOrdinateur()));
		List<Reservation> res = reservationRepository.findAllByOrdinateur(ordinateur);
		System.out.println(res);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<?> reservation=new ArrayList();
		try {
			Date castedDebut=format.parse(reservationRequest.getDebut());
			Date castedFin=format.parse(reservationRequest.getFin());
			System.out.println(castedDebut);
			System.out.println(castedFin);
			 
			 reservation= reservationRepository.CheckOrdiAvailability(ordinateur.getId(), castedDebut, castedFin);
			 System.out.println(reservation);
			 
			 if(reservation.isEmpty()) {
				//il ne peut pas reserver
				Enseignant enseignant = enseignantRepository.findByEmail(reservationRequest.getEnseignant()).orElseThrow(() -> new ResourceNotFoundException("Enseignant not found for this email :: " + reservationRequest.getEnseignant()));
				
				Reservation reserv = new Reservation(castedDebut, castedFin, enseignant);
				reserv.setOrdinateur(ordinateur);
				reservationRepository.save(reserv);
				
				return reservation;
					
				} else {
					return reservation;
				}
		}catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		
		return reservation;
		
	}
	
	@PostMapping("/addreservation/videoprojecteur")
	public Object addReservationVideoProjecteur(@Valid @RequestBody ReservationRequest reservationRequest) {
		VideoProjecteur videoprojecteur = videoProjecteurRepository.findByName(reservationRequest.getOrdinateur()).orElseThrow(() -> new ResourceNotFoundException("Ordinateur not found for this :: " + reservationRequest.getVideoprojecteur()));
		List<Reservation> res = reservationRepository.findAllByVideoProjecteur(videoprojecteur);
		System.out.println(res);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<?> reservation=new ArrayList();
		try {
			Date castedDebut=format.parse(reservationRequest.getDebut());
			Date castedFin=format.parse(reservationRequest.getFin());
			System.out.println(castedDebut);
			System.out.println(castedFin);
			 
			 reservation= reservationRepository.CheckVideoProjecteur(videoprojecteur.getId(), castedDebut, castedFin);
			 System.out.println(reservation);
			 
			 if(reservation.isEmpty()) {
				//il peut reserver
				Enseignant enseignant = enseignantRepository.findByEmail(reservationRequest.getEnseignant()).orElseThrow(() -> new ResourceNotFoundException("Enseignant not found for this email :: " + reservationRequest.getEnseignant()));
				
				Reservation reserv = new Reservation(castedDebut, castedFin, enseignant);
				reserv.setVideoProjecteur(videoprojecteur);
				reservationRepository.save(reserv);
				
				return reservation;
					
				} else {
					return reservation;
				}
		}catch(Exception ex) {
			 System.out.println(ex.getMessage());
		}
		
		return reservation;
		
	}
	

}
