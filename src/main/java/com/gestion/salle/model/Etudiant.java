package com.gestion.salle.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("Etudiant")
public class Etudiant extends User {

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String email, @Size(max = 60) String username, String password, String contact) {
		super(email, username, password, contact);
		// TODO Auto-generated constructor stub
	}
	
	
}
