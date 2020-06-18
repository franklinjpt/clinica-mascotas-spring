package edu.unimagdalena.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.demo.entities.Mascota;
import edu.unimagdalena.demo.entities.Propietario;
import edu.unimagdalena.demo.repositories.MascotaRepository;
import edu.unimagdalena.demo.repositories.PropietarioRepository;
import edu.unimagdalena.demo.repositoriesServices.IPropietarioService;

@Service
public class PropietarioService implements IPropietarioService{
	@Autowired
	private PropietarioRepository pRepository;
	
	@Autowired
	private MascotaRepository mRepository;
	
	@Override
	public Propietario saveOrUpdate(Propietario propietario) {
		
		return pRepository.save(propietario);
	}

	@Override
	public void delete(int id) {
		pRepository.deleteById(id);
		
	}

	@Override
	public List<Propietario> listarPropietarios() {
		
		return pRepository.findAll();
	}

	@Override
	public Propietario findById(int id) {
		return pRepository.getOne(id);
	}

	@Override
	public Mascota crearMascota(Mascota mascota) {
		
	  return mRepository.save(mascota);
	}

	@Override
	public void eliminarMascota(Mascota mascota) {
		mRepository.delete(mascota);
	}

	@Override
	public List<Mascota> listarMascotas() {		
		return mRepository.findAll();
	}
	
	@Override
	public List<Mascota> findMascotasByPropietario(Propietario propietario) {
		
		return mRepository.findMascotasByPropietario(propietario.getId());
	}

}
