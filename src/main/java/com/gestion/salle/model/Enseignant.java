package com.gestion.salle.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("Enseignant")
public class Enseignant extends User{

	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enseignant(String email, @Size(max = 60) String username, String password, String contact) {
		super(email, username, password, contact);
		// TODO Auto-generated constructor stub
	}

	
}
