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

import com.familytree.gs.controller.forms.TemoignageForm;
import com.familytree.gs.model.Temoignage;
import com.familytree.gs.service.TemoignageService;

@RestController
public class TemoignageController {

	@Autowired
	TemoignageService temoignageService;
	
	// Read
	@RequestMapping(
		value = "/temoignages", 
		method = RequestMethod.GET
	)
	public ResponseEntity<List<Temoignage>> recupererTout() {
		List<Temoignage> temoignages = temoignageService.findAll();
		return new ResponseEntity<List<Temoignage>>(temoignages, HttpStatus.OK);
	}

	@RequestMapping(
		value = "/temoignages/{id}", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Temoignage> recuperer(@PathVariable("id") long id) {
		System.out.println("Récupère une Temoignage avec id " + id);
		Temoignage temoignage = temoignageService.findById(id);
		
		if (temoignage == null) {
			System.out.println("Temoignage avec id " + id + " not found");
			return new ResponseEntity<Temoignage>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Temoignage>(temoignage, HttpStatus.OK);
	}
	
	// Create
	@RequestMapping(
		value = "/temoignages", 
		method = RequestMethod.POST
	)
	public ResponseEntity<Void> creer(@RequestBody TemoignageForm pf, UriComponentsBuilder ucBuilder) {
		Temoignage p = pf.get();
		System.out.println("Création Temoignage " + p);
		
		if (temoignageService.isExist(p)) {
			System.out.println("La temoignage que vous essayez de créer existe déjà !");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		temoignageService.save(p);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/temoignages/{id}").buildAndExpand(p.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	// Update
	@RequestMapping(
		value = "/temoignages/{id}", 
		method = RequestMethod.PUT
	)
	public ResponseEntity<Temoignage> mettreAJour(@PathVariable("id") long id, @RequestBody TemoignageForm pf) {
		Temoignage p = pf.get();
		System.out.println("Mise à jour de la temoignage : " + id);
		
		Temoignage temoignageCourante = temoignageService.findById(id);
		
		if (temoignageCourante == null) {
			System.out.println("Temoignage avec id " + id + " not found");
			return new ResponseEntity<Temoignage>(HttpStatus.NOT_FOUND);
		}
		
		temoignageService.update(p);
		return new ResponseEntity<Temoignage>(p, HttpStatus.OK);
	}
	
	// Delete

	@RequestMapping(
		value = "/temoignages", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Void> supprimerTout() {
		System.out.println("Deleting All Temoignages");

		temoignageService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

	@RequestMapping(
		value = "/temoignages/{id}", 
		method = RequestMethod.DELETE
	)
	public ResponseEntity<Temoignage> supprimer(@PathVariable("id") long id) {
		Temoignage temoignage = temoignageService.findById(id);
		if (temoignage == null) {
			System.out.println("Unable to delete. Temoignage with id " + id + " not found");
			return new ResponseEntity<Temoignage>(HttpStatus.NOT_FOUND);
		}

		temoignageService.deleteById(id);
		return new ResponseEntity<Temoignage>(HttpStatus.NO_CONTENT);
	}
	
}
