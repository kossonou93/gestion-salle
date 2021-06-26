package com.gestion.salle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion.salle.model.Ordinateur;
import com.gestion.salle.model.VideoProjecteur;

@Repository
public interface VideoProjecteurRepository extends JpaRepository<VideoProjecteur, Long> {

	Optional<VideoProjecteur> findByName(String name);
}
