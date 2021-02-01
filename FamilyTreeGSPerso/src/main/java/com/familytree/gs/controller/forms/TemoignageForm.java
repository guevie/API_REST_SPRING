package com.familytree.gs.controller.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.familytree.gs.model.Personne;
import com.familytree.gs.model.Temoignage;
import com.familytree.gs.model.Utilisateur;
import com.familytree.gs.service.PersonneService;
import com.familytree.gs.service.UtilisateurService;
import com.familytree.gs.service.impl.PersonneServiceImpl;
import com.familytree.gs.service.impl.UtilisateurServiceImpl;

public class TemoignageForm {

	private UtilisateurService utilisateurService;
	private PersonneService personneService;
	
	private SimpleDateFormat formatter;

	private long id;
	private long sujetId;
	private long redacteurId;
	private String date;
	private String texte;
	private int nbLikes;
	private int nbAttestations;
	private boolean prive;
	
	public TemoignageForm() {
		formatter = new SimpleDateFormat("dd-M-yyyy");
		utilisateurService = new UtilisateurServiceImpl();
		personneService = new PersonneServiceImpl();
	}
	
	public Temoignage get() {
		Temoignage t = new Temoignage();
		if (id > 0) {
			t.setId(id);
		}
		if (sujetId > 0) {
			Personne sujet = personneService.findById(sujetId);
			t.setSujet(sujet);
		}
		if (redacteurId > 0) {
			Utilisateur redacteur = utilisateurService.findById(redacteurId);
			t.setRedacteur(redacteur);
		}
		if (date != null && !date.isBlank()) {
			try {
				Date d = formatter.parse(date);
				t.setDate(d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (texte != null && !texte.isBlank()) {
			t.setTexte(texte);
		}
		if (nbLikes >= 0) {
			t.setNbLikes(nbLikes);
		}
		if (nbAttestations >= 0) {
			t.setNbAttestations(nbAttestations);
		}
		t.setPrive(prive);
		
		return t;
	}
}
