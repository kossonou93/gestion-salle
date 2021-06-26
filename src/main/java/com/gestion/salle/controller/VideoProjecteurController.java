package com.gestion.salle.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.salle.GestionsalleApplication;
import com.gestion.salle.model.VideoProjecteur;
import com.gestion.salle.repository.VideoProjecteurRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/videoprojecteur")
public class VideoProjecteurController {

	@Autowired
	VideoProjecteurRepository videoProjecteurRepository;
	
	Logger logger = LoggerFactory.getLogger(GestionsalleApplication.class);
	
   // Add Full VideoProjecteur
	@PostMapping("/addfull")
	public List<VideoProjecteur> addFullOrdinateur(@Valid @RequestBody List<VideoProjecteur> videoProjecteur) {
		return videoProjecteurRepository.saveAll(videoProjecteur);
	}
	
	@GetMapping("/getall")
	public List<VideoProjecteur> getUsers(){
		return videoProjecteurRepository.findAll();
	}
}
