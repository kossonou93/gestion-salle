package com.gestion.salle.security.request;

import java.util.Date;

public class ReservationRequest {

	private String enseignant;
	private String ordinateur;
	private String videoprojecteur;
	private String salle;
	private String debut;
	private String fin;
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public String getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(String enseignant) {
		this.enseignant = enseignant;
	}
	
	public String getOrdinateur() {
		return ordinateur;
	}
	public void setOrdinateur(String ordinateur) {
		this.ordinateur = ordinateur;
	}
	public String getVideoprojecteur() {
		return videoprojecteur;
	}
	public void setVideoprojecteur(String videoprojecteur) {
		this.videoprojecteur = videoprojecteur;
	}
	public String getSalle() {
		return salle;
	}
	public void setSalle(String salle) {
		this.salle = salle;
	}
	
	
	
}
