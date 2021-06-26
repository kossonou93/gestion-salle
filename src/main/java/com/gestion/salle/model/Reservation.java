package com.gestion.salle.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
@Table(	name = "reservations" )
public class Reservation extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
  
	private Date debut;

	private Date fin;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "user_id")
	private Enseignant enseignant;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ordinateur_id")
	private Ordinateur ordinateur;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "videoProjecteur_id")
	private VideoProjecteur videoProjecteur;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "salle_id")
	private Salle salle;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Reservation(Date debut, Date fin, Enseignant enseignant) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.enseignant = enseignant;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	
	public Ordinateur getOrdinateur() {
		return ordinateur;
	}
	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}
	public VideoProjecteur getVideoProjecteur() {
		return videoProjecteur;
	}
	public void setVideoProjecteur(VideoProjecteur videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
