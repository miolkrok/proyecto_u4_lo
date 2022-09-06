package com.uce.edu.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Persona;
import com.uce.edu.demo.service.IPersonaService;

@Controller
@RequestMapping("/personas/")
public class PersonaController {

	@Autowired
	private IPersonaService personaService;

	@GetMapping("todos")
	public String buscarTodos(Model modelo) {
		List<Persona> lista = this.personaService.buscarTodos();
		modelo.addAttribute("persona", lista);
		return "vistaListaPersonas";

	}

	@GetMapping("buscarPersona/{idPersona}")
	public String buscarEmpleado(@PathVariable("idPersona") Integer id,Model modelo) {
		System.out.println("El ID:" + id);
		Persona per = this.personaService.buscarPorId(id);
		modelo.addAttribute("persona",per);
		return "vistaPersona";
	}

	@PutMapping("actualizar/{idPersona}")
	public String actualizarPersona(@PathVariable("idPersona") Integer id,Persona persona) {
		persona.setId(id);
		this.personaService.actualizar(persona);
		return "redirect:/personas/todos";
		
	}

	@DeleteMapping("borrar/{idPersona}")
	public String borrarPersona(@PathVariable("idPersona") Integer id) {
		this.personaService.eliminar(id);
		return "redirect:/personas/todos";
	}
	
	@GetMapping("nuevaPersona")
	public String paginaNuevaPersona(Persona persona) {
		return "vistaNuevaPersona";
	}
	
	@PostMapping("insertar")
	public String insertarPersona(Persona persona) {
		this.personaService.guardar(persona);
		return "redirect:/personas/todos";
	}
	
}
