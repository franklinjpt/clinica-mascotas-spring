package edu.unimagdalena.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.demo.entities.Propietario;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Integer>{
	
}
