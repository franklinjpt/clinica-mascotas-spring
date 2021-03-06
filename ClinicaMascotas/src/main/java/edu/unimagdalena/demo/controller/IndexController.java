package edu.unimagdalena.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
		
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/listarPropietario")
	public String listarPropietario() {
		return "listarPropietario";
	}
	
	@GetMapping("/agregarMascota")
	public String agregarMascota() {
		return "agregarMascota";
	}
}
