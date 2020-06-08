package edu.unimagdalena.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.demo.entities.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer>{
	@Query("SELECT m FROM Mascota m where m.propietario.id = :id") 
	List<Mascota> findMascotasByPropietario(@Param("id") Integer propietarioId);
}
