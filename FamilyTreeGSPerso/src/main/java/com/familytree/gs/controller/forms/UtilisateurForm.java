package com.familytree.gs.controller.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;

import com.familytree.gs.model.Utilisateur;

@Configurable
public class UtilisateurForm {

	public static SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
	
	private long id;
	private String email;
	private String nom;
	private String prenom;
	private String dateNaissance;
	
	public UtilisateurForm() {}
	
	public Utilisateur get() {
		Utilisateur utilisateur = new Utilisateur();
		if (this.id > 0) {
			utilisateur.setId(id);
		}
		if (email != null && !email.isBlank())
			utilisateur.setEmail(email);
		if (nom != null && !nom.isBlank())
			utilisateur.setNom(nom);
		if (prenom != null && !prenom.isBlank())
			utilisateur.setPrenom(prenom);
		if (dateNaissance != null && !dateNaissance.isBlank()) {
			try {
				Date date = formatter.parse(dateNaissance);
				utilisateur.setDateNaissance(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

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

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	
}
