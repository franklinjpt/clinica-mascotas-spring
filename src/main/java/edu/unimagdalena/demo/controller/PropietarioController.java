package edu.unimagdalena.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unimagdalena.demo.entities.Mascota;
import edu.unimagdalena.demo.entities.Propietario;
import edu.unimagdalena.demo.repositoriesServices.IPropietarioService;

@RestController
@RequestMapping
public class PropietarioController {
	@Autowired
	private IPropietarioService pService;
	
	@GetMapping("/propietarios")
	public ResponseEntity<List<Propietario>> listarPropietarios(){
		List<Propietario>propietarios = pService.listarPropietarios();	
		return new ResponseEntity<List<Propietario>>(propietarios,HttpStatus.OK);
	}
	
	@PostMapping("/crear-propietario")
	public ResponseEntity<Propietario> crearPropietario(@Valid @RequestBody Propietario propietario) {
		
		Propietario nuevoPropietario = pService.saveOrUpdate(propietario);
		
		return new ResponseEntity<Propietario>(nuevoPropietario,HttpStatus.CREATED);
	}
	
	@PutMapping("/editar-propietario")
	public ResponseEntity<Propietario> editarPropietario(@Valid @RequestBody Propietario propietario) {
		
		Propietario  propietarioEditado = pService.saveOrUpdate(propietario);		
		
		return new ResponseEntity<Propietario>(propietarioEditado,HttpStatus.CREATED);
	}
	
	@GetMapping("/mascota-propietario/{propietarios}")
	public ResponseEntity<List<Mascota>> getMascotasPorPropietario(@PathVariable("propietarios") int id){
		Propietario  propietario = pService.findById(id);
		
		List<Mascota> mascotas= pService.findMascotasByPropietario(propietario);
		if(mascotas != null) {
		return new ResponseEntity<List<Mascota>>(mascotas,HttpStatus.OK);
			
		
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/mascota-propietario/{id}")
	public ResponseEntity<Mascota> crearMascota(@Valid @RequestBody Mascota mascota, @PathVariable int id) {
		
		Propietario  propietario = pService.findById(id);
		if(propietario != null) {
			
			mascota.setPropietario(propietario);
			
			Mascota nuevaMascota = pService.crearMascota(mascota);
			
			return new ResponseEntity<Mascota>(nuevaMascota,HttpStatus.CREATED);
		}


		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/mascota-propietario/{id}")
	public ResponseEntity<Mascota> editarMascota(@Valid @RequestBody Mascota mascota, @PathVariable int id) {
		
	Propietario  propietario = pService.findById(id);
		if(propietario != null) {
			
			mascota.setPropietario(propietario);
			
			Mascota nuevaMascota = pService.crearMascota(mascota);
			
			
			return new ResponseEntity<Mascota>(nuevaMascota,HttpStatus.CREATED);
		}
			
		
		return  null;
	}
}
