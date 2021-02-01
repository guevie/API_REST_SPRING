package com.familytree.gs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.familytree.gs.controller.forms.PersonneForm;
import com.familytree.gs.model.Personne;
import com.familytree.gs.model.Temoignage;
import com.familytree.gs.service.EpouxService;
import com.familytree.gs.service.LienService;
import com.familytree.gs.service.PersonneService;
import com.familytree.gs.service.TemoignageService;

@RestController
public class PersonneController {

	@Autowired
	PersonneService personneService;
	@Autowired
	LienService lienService;
	@Autowired
	TemoignageService temoignageService;
	@Autowired
	EpouxService epouxService;
	
	// Read
	@RequestMapping(
		value = "/personnes", 
		method = RequestMethod.GET
	)
	public ResponseEntity<List<Personne>> recupererTout() {
		List<Personne> personnes = personneService.findAll();
		if(personnes.isEmpty()){
			return new ResponseEntity<List<Personne>>(HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<List<Personne>>(personnes, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/personnes/{id}", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Personne> recuperer(@PathVariable("id") long id) {
		System.out.println("Récupère une Personne avec id " + id);
		Personne personne = personneService.findById(id);
		
		if (personne == null) {
			System.out.println("Personne avec id " + id + " not found");
			return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Personne>(personne, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/personnes/{id}/arbre", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Personne>> recupererArbre(@PathVariable("id") long id) {
		System.out.println("Récupère l'arbre d'une Personne avec id " + id);
		List<Personne> personnes = lienService.getArbre(id, 2);
	
		return new ResponseEntity<List<Personne>>(personnes, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/personnes/{id}/conjoints", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Personne>> recupererConjoints(@PathVariable("id") long id) {
		System.out.println("Récupère les conjoints d'une Personne avec id " + id);
		List<Personne> conjoints = epouxService.getListeConjoint(id);
	
		return new ResponseEntity<List<Personne>>(conjoints, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/personnes/{id}/temoignages", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Temoignage>> recupererTemoignages(@PathVariable("id") long id) {
		System.out.println("Récupère les témoignages d'une Personne avec id " + id);
		List<Temoignage> temoignages = temoignageService.getTemoignageParPersonne(id);
	
		return new ResponseEntity<List<Temoignage>>(temoignages, HttpStatus.OK);
	}
	
	// Create
	@RequestMapping(
		value = "/personnes", 
		method = RequestMethod.POST
	)
	public ResponseEntity<Void> creer(@RequestBody PersonneForm pf, UriComponentsBuilder ucBuilder) {
		Personne p = pf.get();
		System.out.println("Création Personne " + p);
		
		if (personneService.isExist(p)) {
			System.out.println("La personne que vous essayez de créer existe déjà !");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		personneService.save(p);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/personnes/{id}").buildAndExpand(p.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	// Update
	@RequestMapping(
		value = "/personnes/{id}", 
		method = RequestMethod.PUT
	)
	public ResponseEntity<Personne> mettreAJour(@PathVariable("id") long id, @RequestBody PersonneForm pf) {
		Personne p = pf.get();
		System.out.println("Mise à jour de la personne : " + id);
		
		Personne personneCourante = personneService.findById(id);
		
		if (personneCourante == null) {
			System.out.println("Personne avec id " + id + " not found");
			return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
		}
		
		personneService.update(p);
		return new ResponseEntity<Personne>(p, HttpStatus.OK);
	}
	
	// Delete

	@RequestMapping(
		value = "/personnes", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Void> supprimerTout() {
		System.out.println("Deleting All Personnes");

		personneService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

	@RequestMapping(
		value = "/personnes/{id}", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Personne> supprimer(@PathVariable("id") long id) {
		Personne personne = personneService.findById(id);
		if (personne == null) {
			System.out.println("Unable to delete. Personne with id " + id + " not found");
			return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
		}

		personneService.deleteById(id);
		return new ResponseEntity<Personne>(HttpStatus.NO_CONTENT);
	}
	
}
