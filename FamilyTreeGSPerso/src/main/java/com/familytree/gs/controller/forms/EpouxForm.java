package com.familytree.gs.controller.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.familytree.gs.model.Epoux;
import com.familytree.gs.model.Personne;
import com.familytree.gs.model.Utilisateur;
import com.familytree.gs.service.PersonneService;
import com.familytree.gs.service.UtilisateurService;
import com.familytree.gs.service.impl.PersonneServiceImpl;
import com.familytree.gs.service.impl.UtilisateurServiceImpl;

public class EpouxForm {

	private UtilisateurService utilisateurService;
	private PersonneService personneService;
	
	private SimpleDateFormat formatter;

	private long id;
	private long redacteurId;
	private long sujetId;
	private long conjointId;
	private String dateDebut;
	private String dateFin;
	
	public EpouxForm() {
		formatter = new SimpleDateFormat("dd-M-yyyy");
		utilisateurService = new UtilisateurServiceImpl();
		personneService = new PersonneServiceImpl();
	}
	
	public Epoux get() {
		Epoux e = new Epoux();
		if (id > 0) {
			e.setId(id);
		}
		if (redacteurId > 0) {
			Utilisateur u = utilisateurService.findById(redacteurId);
			e.setRedacteur(u);			
		}
		if (sujetId > 0) {
			Personne sujet = personneService.findById(sujetId);
			e.setSujet(sujet);
		}
		if (conjointId > 0) {
			Personne conjoint = personneService.findById(conjointId);
			e.setConjoint(conjoint);
		}
		if (dateDebut != null && !dateDebut.isBlank()) {
			try {
				Date debut = formatter.parse(dateDebut);
				e.setDateDebut(debut);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (dateFin != null && !dateFin.isBlank()) {
			try {
				Date fin = formatter.parse(dateFin);
				e.setDateDebut(fin);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return e;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRedacteurId() {
		return redacteurId;
	}

	public void setRedacteurId(long redacteurId) {
		this.redacteurId = redacteurId;
	}

	public long getSujetId() {
		return sujetId;
	}

	public void setSujetId(long sujetId) {
		this.sujetId = sujetId;
	}

	public long getConjointId() {
		return conjointId;
	}

	public void setConjointId(long conjointId) {
		this.conjointId = conjointId;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
}
