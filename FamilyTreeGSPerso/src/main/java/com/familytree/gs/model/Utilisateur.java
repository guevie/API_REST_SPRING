package com.familytree.gs.model;

import java.util.Date;

public class Utilisateur {
	
	private long id;
	private String email;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	
	public Utilisateur() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
				+ dateNaissance + "]";
	}
	
}
