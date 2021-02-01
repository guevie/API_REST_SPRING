package com.familytree.gs.model;

import com.familytree.gs.model.enums.StatusLienEnum;
import com.familytree.gs.model.enums.TypeRelationEnum;

public class Lien {

	private long id;
	private Utilisateur redacteur;
	private Personne sujet;
	private Personne relatif;
	private TypeRelationEnum typeRelation;
	private StatusLienEnum status;
	
	public Lien() {}
	
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
	
	public Personne getRelatif() {
		return relatif;
	}
	
	public void setRelatif(Personne relatif) {
		this.relatif = relatif;
	}
	
	public TypeRelationEnum getTypeRelation() {
		return typeRelation;
	}
	
	public void setTypeRelation(TypeRelationEnum typeRelation) {
		this.typeRelation = typeRelation;
	}
	
	public StatusLienEnum getStatus() {
		return status;
	}
	
	public void setStatus(StatusLienEnum status) {
		this.status = status;
	}
	
	
	
}
