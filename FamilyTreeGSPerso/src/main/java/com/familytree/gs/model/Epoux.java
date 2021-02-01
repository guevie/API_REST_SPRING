package com.familytree.gs.model;

import java.util.Date;

public class Epoux {

	private long id;
	private Utilisateur redacteur;
	private Personne sujet;
	private Personne conjoint;
	private Date dateDebut;
	private Date dateFin;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Utilisateur getRedacteur() {
		return redacteur;
	}
	
	public void setRedacteur(Utilisateur redacteur) {
		this.redacteur = redacteur;
	}
	
	public Personne getSujet() {
		return sujet;
	}
	
	public void setSujet(Personne sujet) {
		this.sujet = sujet;
	}
	
	public Personne getConjoint() {
		return conjoint;
	}
	
	public void setConjoint(Personne conjoint) {
		this.conjoint = conjoint;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public Date getDateFin() {
		return dateFin;
	}
	
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
}
