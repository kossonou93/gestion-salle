package com.gestion.salle.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.salle.GestionsalleApplication;
import com.gestion.salle.model.Ordinateur;
import com.gestion.salle.model.Salle;
import com.gestion.salle.repository.OrdinateurRepository;
import com.gestion.salle.response.MessageResponse;
import com.gestion.salle.security.request.MaterielRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/ordinateur")
//@Api(value="User Management System", description="Operations pertaining to user in User Management System")
public class OrdinateurController {

	@Autowired
	OrdinateurRepository ordinateurRepository;
	
	Logger logger = LoggerFactory.getLogger(GestionsalleApplication.class);
	
   // Add Full Salle
	@PostMapping("/addfull")
	public List<Ordinateur> addFullOrdinateur(@Valid @RequestBody List<Ordinateur> ordinateur) {
		return ordinateurRepository.saveAll(ordinateur);
	}
	
	@GetMapping("/getall")
	public List<Ordinateur> getUsers(){
		return ordinateurRepository.findAll();
	}
}
