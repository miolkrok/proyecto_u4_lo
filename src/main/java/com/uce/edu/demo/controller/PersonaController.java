package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Persona;
import com.uce.edu.demo.service.IPersonaService;

@Controller
@RequestMapping("/persona/")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("todos")
	public String buscarTodos(Model modelo) {
		List<Persona> lista = this.personaService.buscarTodos();
		modelo.addAttribute("personas", lista);
		return "vistaListaPersonas";
		
	}
	
	
}
