package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Persona;

@Repository
@Transactional
public class PersonaRepositoryImpl implements IPersonaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Persona buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Persona.class, id);
	}

	@Override
	public void insertar(Persona persona) {
		// TODO Auto-generated method stub
		this.entityManager.persist(persona);
	}

	@Override
	public void actualizar(Persona persona) {
		// TODO Auto-generated method stub
		this.entityManager.merge(persona);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		Persona persona = this.buscarPorId(id);
		this.entityManager.remove(persona);
	}

	@Override
	public Persona buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		Query jpqlQuery = this.entityManager.createQuery("SELECT p FROM Persona p WHERE p.cedula = :datoCedula");
		jpqlQuery.setParameter("datoCedula", cedula);

		return (Persona) jpqlQuery.getSingleResult();
	}

	@Override
	public Persona buscarPorCedulaTyped(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Persona> myTypedQuery = this.entityManager
				.createQuery("SELECT p FROM Persona p WHERE p.cedula = :datoCedula ", Persona.class);
		myTypedQuery.setParameter("datoCedula", cedula);
		return myTypedQuery.getSingleResult();
	}

	@Override
	public Persona buscarPorCedulaNamed(String cedula) {
		// TODO Auto-generated method stub
		Query myQuery = this.entityManager.createNamedQuery("Persona.buscarPorCedula");
		myQuery.setParameter("datoCedula", cedula);
		return (Persona) myQuery.getSingleResult();
	}

	@Override
	public Persona buscarPorCedulaTypedNamed(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Persona> myTypedQuery = this.entityManager.createNamedQuery("Persona.buscarPorCedula",
				Persona.class);
		myTypedQuery.setParameter("datoCedula", cedula);
		return myTypedQuery.getSingleResult();
	}

	@Override
	public Persona buscarPorCedulaNative(String cedula) {
		// TODO Auto-generated method stub
		Query myQuery = this.entityManager.createNativeQuery("SELECT * FROM persona WHERE pers_cedula = :datoCedula",
				Persona.class);
		myQuery.setParameter("datoCedula", cedula);
		return (Persona) myQuery.getSingleResult();
	}

	@Override
	public Persona buscarPorCedulaNativeNamed(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Persona> myTypedQuery = this.entityManager.createNamedQuery("Persona.buscaPorCedulaNative",
				Persona.class);
		myTypedQuery.setParameter("datoCedula", cedula);
		return myTypedQuery.getSingleResult();
	}

	@Override
	public Persona buscarPorCedulaCriteriaAPI(String cedula) {
		// TODO Auto-generated method stub
		CriteriaBuilder myBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Persona> myQuery = myBuilder.createQuery(Persona.class);

		// Root FROM
		Root<Persona> personaRoot = myQuery.from(Persona.class);
		// myQuery.select(personaRoot)//Select p FROM Persona
		// Las condiciones where en criteria API se los conoce como predicados
		Predicate p1 = myBuilder.equal(personaRoot.get("cedula"), cedula);

		CriteriaQuery<Persona> myQueryCompleto = myQuery.select(personaRoot).where(p1);
		// Finalizado mi query completo

		TypedQuery<Persona> myQueryFinal = this.entityManager.createQuery(myQueryCompleto);

		return myQueryFinal.getSingleResult();
	}

	@Override
	public List<Persona> buscarDinamicamente(String nombre, String apellido, String genero) {
		// TODO Auto-generated method stub
		CriteriaBuilder myBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Persona> myQuery = myBuilder.createQuery(Persona.class);

		Root<Persona> myTabla = myQuery.from(Persona.class);

		Predicate predNombre = myBuilder.equal(myTabla.get("nombre"), nombre);

		Predicate predApellido = myBuilder.equal(myTabla.get("apellido"), apellido);

		Predicate miPredicadoFinal = null;
		if (genero.equals("M")) {
			miPredicadoFinal = myBuilder.and(predNombre, predApellido);
		} else {
			miPredicadoFinal = myBuilder.or(predNombre, predApellido);
		}
		CriteriaQuery<Persona> myQueryCompleto = myQuery.select(myTabla).where(miPredicadoFinal);

		TypedQuery<Persona> myQueryFinal = this.entityManager.createQuery(myQueryCompleto);
		return myQueryFinal.getResultList();
	}

	@Override
	public List<Persona> buscarDinamicamentePredicados(String nombre, String apellido, String genero) {
		// TODO Auto-generated method stub
		CriteriaBuilder myBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Persona> myQuery = myBuilder.createQuery(Persona.class);

		Root<Persona> myTabla = myQuery.from(Persona.class);

		Predicate predNombre = myBuilder.equal(myTabla.get("nombre"), nombre);
		Predicate predApellido = myBuilder.equal(myTabla.get("apellido"), apellido);
		Predicate predGenero = myBuilder.equal(myTabla.get("genero"), genero);
		Predicate miPredicadoFinal = null;
		if (genero.equals("M")) {
			miPredicadoFinal = myBuilder.and(predNombre, predApellido);
			myBuilder.or(miPredicadoFinal, predGenero);
		} else {
			miPredicadoFinal = myBuilder.or(predNombre, predApellido);
			myBuilder.and(miPredicadoFinal, predGenero);
		}
		CriteriaQuery<Persona> myQueryCompleto = myQuery.select(myTabla).where(miPredicadoFinal);

		TypedQuery<Persona> myQueryFinal = this.entityManager.createQuery(myQueryCompleto);
		return myQueryFinal.getResultList();
	}

	@Override
	public List<Persona> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		Query jpqlQuery = this.entityManager.createQuery("SELECT p FROM Persona p WHERE p.apellido = :datoApellido");
		jpqlQuery.setParameter("datoApellido", apellido);

		return jpqlQuery.getResultList();
	}

//	@Override
//	public List<PersonaSencilla> buscarPorApellidoSencillo(String apellido) {
//		// TODO Auto-generated method stub
//		TypedQuery<PersonaSencilla> myQuery = this.entityManager.createQuery(
//				"SELECT NEW com.uce.edu.demo.repository.modelo.PersonaSencilla(p.nombre, p.apellido) FROM Persona p WHERE p.apellido = :datoApellido",
//				PersonaSencilla.class);
//		myQuery.setParameter("datoApellido", apellido);
//		return myQuery.getResultList();
//	}

	@Override
	public List<Persona> buscarPorNombreApellidoTypedNamed(String nombre, String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<Persona> myTypedQuery = this.entityManager.createNamedQuery("Persona.buscarPorNombreApellido",
				Persona.class);
		myTypedQuery.setParameter("datoNombre", nombre);
		myTypedQuery.setParameter("datoApellido", apellido);
		return myTypedQuery.getResultList();
	}

	@Override
	public List<Persona> buscarPorGenero(String genero) {
		// TODO Auto-generated method stub
		Query jpqlQuery = this.entityManager.createQuery("SELECT p FROM Persona p WHERE p.genero = :datoGenero");
		jpqlQuery.setParameter("datoGenero", genero);

		return jpqlQuery.getResultList();
	}

	@Override
	public List<Persona> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		Query jpqlQuery = this.entityManager.createQuery("SELECT p FROM Persona p WHERE p.nombre = :datoNombre");
		jpqlQuery.setParameter("datoNombre", nombre);

		return jpqlQuery.getResultList();
	}

	@Override
	public int actualizarPorApellido(String genero, String apellido) {
		// TODO Auto-generated method stub
		Query myQuery = this.entityManager
				.createQuery("UPDATE Persona p SET p.genero = : datoGenero WHERE p.apellido = :datoApellido");
		myQuery.setParameter("datoGenero", genero);
		myQuery.setParameter("datoApellido", apellido);
		return myQuery.executeUpdate();
	}

	@Override
	public int eliminarPorGenero(String genero) {
		// TODO Auto-generated method stub
		Query myQuery = this.entityManager.createQuery("DELETE FROM Persona p WHERE p.genero = :datoGenero");
		myQuery.setParameter("datoGenero", genero);

		return myQuery.executeUpdate();
	}

	@Override
	public List<Persona> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Persona> myTypedQuery = this.entityManager.createQuery("SELECT p FROM Persona p", Persona.class);
		return myTypedQuery.getResultList();
	}

}
