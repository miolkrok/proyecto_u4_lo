package com.uce.edu.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IPersonaRepository;
import com.uce.edu.demo.repository.modelo.Persona;
@Service
public class PersonaServiceImpl implements IPersonaService{

	@Autowired
	private IPersonaRepository personaJpaRepository;
	
	@Override
	public Persona buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorId(id);
	}

	@Override
	public void guardar(Persona persona) {
		// TODO Auto-generated method stub
		this.personaJpaRepository.insertar(persona);
	}

	@Override
	public void actualizar(Persona persona) {
		// TODO Auto-generated method stub
		this.personaJpaRepository.actualizar(persona);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.personaJpaRepository.eliminar(id);
	}

	@Override
	public Persona buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorCedula(cedula);
	}
	
	@Override
	public Persona buscarPorCedulaTyped(String cedula) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorCedulaTyped(cedula);
	}
	
	@Override
	public Persona buscarPorCedulaNamed(String cedula) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorCedulaNamed(cedula);
	}

	@Override
	public Persona buscarPorCedulaTypedNamed(String cedula) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorCedulaTypedNamed(cedula);
	}
	
	@Override
	public Persona buscarPorCedulaNative(String cedula) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorCedulaNative(cedula);
	}
	
	@Override
	public Persona buscarPorCedulaNativeNamed(String cedula) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorCedulaNativeNamed(cedula);
	}
	
	@Override
	public Persona buscarPorCedulaCriteriaAPI(String cedula) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorCedulaCriteriaAPI(cedula);
	}

	@Override
	public List<Persona> buscarDinamicamente(String nombre, String apellido, String genero) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarDinamicamente(nombre, apellido, genero);
	}
	
	@Override
	public List<Persona> buscarDinamicamentePredicados(String nombre, String apellido, String genero) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarDinamicamentePredicados(nombre, apellido, genero);
	}
	
	@Override
	public List<Persona> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorApellido(apellido);
	}
	
//	@Override
//	public List<PersonaSencilla> buscarPorApellidoSencillo(String apellido) {
//		// TODO Auto-generated method stub
//		return this.personaJpaRepository.buscarPorApellidoSencillo(apellido);
//	}
	
	@Override
	public List<Persona> buscarPorNombreApellidoTypedNamed(String nombre, String apellido) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorNombreApellidoTypedNamed(nombre, apellido);
	}

	@Override
	public List<Persona> buscarPorGenero(String genero) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorGenero(genero);
	}

	@Override
	public List<Persona> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarPorNombre(nombre);
	}

	@Override
	public int actualizarPorApellido(String genero, String apellido) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.actualizarPorApellido(genero, apellido);
	}

	@Override
	public int eliminarPorGenero(String genero) {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.eliminarPorGenero(genero);
	}

	@Override
	public List<Persona> buscarTodos() {
		// TODO Auto-generated method stub
		return this.personaJpaRepository.buscarTodos();
	}
}
