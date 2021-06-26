package com.gestion.salle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion.salle.model.Ordinateur;
import com.gestion.salle.model.Salle;

@Repository
public interface OrdinateurRepository extends JpaRepository<Ordinateur, Long> {

	Optional<Ordinateur> findByName(String name);
}
