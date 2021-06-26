package com.gestion.salle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.salle.model.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	
	Etudiant findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<Etudiant> findByEmail(String email);
	
	Optional<Etudiant> findById(Long id);
	
	Etudiant findByEmailIgnoreCase(String email);

}
