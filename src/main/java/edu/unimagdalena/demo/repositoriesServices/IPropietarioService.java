package edu.unimagdalena.demo.repositoriesServices;

import java.util.List;

import edu.unimagdalena.demo.entities.Mascota;
import edu.unimagdalena.demo.entities.Propietario;

public interface IPropietarioService {
	Propietario saveOrUpdate(Propietario propietario);
	void delete(int id);
	List<Propietario>listarPropietarios();
	Propietario findById(int id);
	List<Mascota> findMascotasByPropietario(Propietario propietario);
	
	Mascota crearMascota(Mascota mascota);
	void eliminarMascota(Mascota mascota);
	List<Mascota>listarMascotas();
}
