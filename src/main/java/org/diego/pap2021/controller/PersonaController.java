package org.diego.pap2021.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.diego.pap2021.entities.Pais;
import org.diego.pap2021.entities.Persona;
import org.diego.pap2021.exception.DangerException;
import org.diego.pap2021.exception.PRG;
import org.diego.pap2021.repository.PaisRepository;
import org.diego.pap2021.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private PaisRepository paisRepository;

	@GetMapping("r")
	public String r(ModelMap m,//se crea un model map m para utilizarlo
			HttpSession s) throws DangerException {
		List<Persona> personas = personaRepository.findAll();
		m.put("personas", personas);
		m.put("view", "persona/r");//objeto m de clase model map
		return "_t/frame";	}
 

	@GetMapping("c")
	public String c( 
			ModelMap m) {
		m.put("paises", paisRepository.findAll());
		m.put("view", "persona/c");
		return "_t/frame";
	}

	@PostMapping("c")
	public String cPost(
			@RequestParam("login") String login,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido,

			@RequestParam(value="idPaisNace",required=false) Long idPaisNace,
			@RequestParam(value="idPaisVive",required=false) Long idPaisVive
			) throws DangerException {
		try {
			Pais paisNace= idPaisNace==null?null:paisRepository.getById(idPaisNace);
			Pais paisVive = idPaisVive==null ? null :paisRepository.getById(idPaisVive);
			Persona persona = new Persona(login,nombre,apellido,paisNace,paisVive);
	
			
			personaRepository.save(persona);

		
		} catch (Exception e) {
			PRG.error("Error indeterminado al crear la persona "+e.getMessage());
		}
		return "redirect:/persona/r";
	}
	
	
	
	@GetMapping("u")
	public String u(
			@RequestParam("idPersona") Long idPersona,
			ModelMap m) {
		m.put("persona", personaRepository.getById(idPersona));
		m.put("paises", paisRepository.findAll());
		m.put("view", "persona/u");
		return "_t/frame";
	}
	
	
	
	
	@PostMapping("u")
	public String uPost(
			@RequestParam("login") String login,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido,
			@RequestParam("idPersona") Long idPersona,

			@RequestParam("idPaisNace") Long idPaisNace
			
			) throws DangerException {
		try {
			Persona persona = personaRepository.getById(idPersona);
			persona.setLogin(login);
			persona.setNombre(nombre);
			persona.setApellido(apellido);

			if (idPaisNace!=persona.getNace().getId()) {
				Pais nuevoPaisNacimiento = paisRepository.getById(idPaisNace);
				persona.setNace(nuevoPaisNacimiento);
			}
			
			personaRepository.save(persona);
		} catch (Exception e) {
			PRG.error("Error indeterminado al crear la persona "+e.getMessage());
		}
		return "redirect:/persona/r";
	}
	
	
	@PostMapping("d")
	public String dPost(
			@RequestParam("idPersona") Long idPersona
			) {
		personaRepository.deleteById(idPersona);
		return "redirect:/persona/r";
	}
	
	

}
