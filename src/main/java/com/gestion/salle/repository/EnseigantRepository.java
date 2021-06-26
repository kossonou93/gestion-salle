package com.gestion.salle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion.salle.model.Enseignant;
import com.gestion.salle.model.User;

@Repository
public interface EnseigantRepository extends JpaRepository<Enseignant, Long> {

	Enseignant findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<Enseignant> findByEmail(String email);
	
	Optional<Enseignant> findById(Long id);
	
	Enseignant findByEmailIgnoreCase(String email);
}
