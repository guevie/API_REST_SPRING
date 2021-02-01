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

import com.familytree.gs.controller.forms.EpouxForm;
import com.familytree.gs.model.Epoux;
import com.familytree.gs.service.EpouxService;

@RestController
public class EpouxController {

	@Autowired
	EpouxService epouxService;
	
	// Read
	@RequestMapping(
		value = "/epoux", 
		method = RequestMethod.GET
	)
	public ResponseEntity<List<Epoux>> recupererTout() {
		List<Epoux> listeEpoux = epouxService.findAll();
		return new ResponseEntity<List<Epoux>>(listeEpoux, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/epoux/{id}", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Epoux> recuperer(@PathVariable("id") long id) {
		System.out.println("Récupère une Epoux avec id " + id);
		Epoux epoux = epouxService.findById(id);
		
		if (epoux == null) {
			System.out.println("Epoux avec id " + id + " not found");
			return new ResponseEntity<Epoux>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Epoux>(epoux, HttpStatus.OK);
	}
	
	// Create
	@RequestMapping(
		value = "/epoux", 
		method = RequestMethod.POST
	)
	public ResponseEntity<Void> creer(@RequestBody EpouxForm pf, UriComponentsBuilder ucBuilder) {
		Epoux p = pf.get();
		System.out.println("Création Epoux " + p);
		
		if (epouxService.isExist(p)) {
			System.out.println("La epoux que vous essayez de créer existe déjà !");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		epouxService.save(p);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/epoux/{id}").buildAndExpand(p.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	// Update
	@RequestMapping(
		value = "/epoux/{id}", 
		method = RequestMethod.PUT
	)
	public ResponseEntity<Epoux> mettreAJour(@PathVariable("id") long id, @RequestBody EpouxForm pf) {
		Epoux p = pf.get();
		System.out.println("Mise à jour de la epoux : " + id);
		
		Epoux epouxCourante = epouxService.findById(id);
		
		if (epouxCourante == null) {
			System.out.println("Epoux avec id " + id + " not found");
			return new ResponseEntity<Epoux>(HttpStatus.NOT_FOUND);
		}
		
		epouxService.update(p);
		return new ResponseEntity<Epoux>(p, HttpStatus.OK);
	}
	
	// Delete

	@RequestMapping(
		value = "/epoux", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Void> supprimerTout() {
		System.out.println("Deleting All Epouxs");

		epouxService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

	@RequestMapping(
		value = "/epoux/{id}", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Epoux> supprimer(@PathVariable("id") long id) {
		Epoux epoux = epouxService.findById(id);
		if (epoux == null) {
			System.out.println("Unable to delete. Epoux with id " + id + " not found");
			return new ResponseEntity<Epoux>(HttpStatus.NOT_FOUND);
		}

		epouxService.deleteById(id);
		return new ResponseEntity<Epoux>(HttpStatus.NO_CONTENT);
	}
	
}

