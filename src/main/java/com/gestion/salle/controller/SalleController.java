package com.gestion.salle.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.salle.GestionsalleApplication;
import com.gestion.salle.model.Salle;
import com.gestion.salle.repository.SalleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/salle")
//@Api(value="User Management System", description="Operations pertaining to user in User Management System")
public class SalleController {

	@Autowired
	SalleRepository salleRepository;
	
	Logger logger = LoggerFactory.getLogger(GestionsalleApplication.class);
	
	// Add Full Salle
	@PostMapping("/addfull")
	public List<Salle> addFullSalle(@Valid @RequestBody List<Salle> salle) {
		return salleRepository.saveAll(salle);
	}
	
	@GetMapping("/getall")
	public List<Salle> getUsers(){
		return salleRepository.findAll();
	}
		
}
