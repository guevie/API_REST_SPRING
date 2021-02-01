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

import com.familytree.gs.controller.forms.LienForm;
import com.familytree.gs.model.Lien;
import com.familytree.gs.service.LienService;

@RestController
public class LienController {

	@Autowired
	LienService lienService;
	
	// Read
	@RequestMapping(
		value = "/liens", 
		method = RequestMethod.GET
	)
	public ResponseEntity<List<Lien>> recupererTout() {
		List<Lien> liens = lienService.findAll();
		if(liens.isEmpty()){
			return new ResponseEntity<List<Lien>>(HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<List<Lien>>(liens, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/liens/{id}", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Lien> recuperer(@PathVariable("id") long id) {
		System.out.println("Récupère une Lien avec id " + id);
		Lien lien = lienService.findById(id);
		
		if (lien == null) {
			System.out.println("Lien avec id " + id + " not found");
			return new ResponseEntity<Lien>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Lien>(lien, HttpStatus.OK);
	}
	
	// Create
	@RequestMapping(
		value = "/liens", 
		method = RequestMethod.POST
	)
	public ResponseEntity<Void> creer(@RequestBody LienForm lienForm, UriComponentsBuilder ucBuilder) {
		Lien p = lienForm.get();
		System.out.println("Création Lien " + p);
		
		if (lienService.isExist(p)) {
			System.out.println("La lien que vous essayez de créer existe déjà !");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		lienService.save(p);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/liens/{id}").buildAndExpand(p.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	// Update
	@RequestMapping(
		value = "/liens/{id}", 
		method = RequestMethod.PUT
	)
	public ResponseEntity<Lien> mettreAJour(@PathVariable("id") long id, @RequestBody Lien p) {
		System.out.println("Mise à jour de la lien : " + id);
		
		Lien lienCourante = lienService.findById(id);
		
		if (lienCourante == null) {
			System.out.println("Lien avec id " + id + " not found");
			return new ResponseEntity<Lien>(HttpStatus.NOT_FOUND);
		}
		
		lienService.update(p);
		return new ResponseEntity<Lien>(lienCourante, HttpStatus.OK);
	}
	
	// Delete

	@RequestMapping(
		value = "/liens", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Void> supprimerTout() {
		System.out.println("Deleting All Liens");

		lienService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

	@RequestMapping(
		value = "/liens/{id}", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Lien> supprimer(@PathVariable("id") long id) {
		Lien lien = lienService.findById(id);
		if (lien == null) {
			System.out.println("Unable to delete. Lien with id " + id + " not found");
			return new ResponseEntity<Lien>(HttpStatus.NOT_FOUND);
		}

		lienService.deleteById(id);
		return new ResponseEntity<Lien>(HttpStatus.NO_CONTENT);
	}
	
}
