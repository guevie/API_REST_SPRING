package com.familytree.gs.controller.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.familytree.gs.model.Personne;
import com.familytree.gs.model.Utilisateur;
import com.familytree.gs.model.enums.SexeEnum;
import com.familytree.gs.service.UtilisateurService;
import com.familytree.gs.service.impl.UtilisateurServiceImpl;

public class PersonneForm {

	private UtilisateurService utilisateurService;

	private SimpleDateFormat formatter;

	private long id;
	private long redacteurId;
	private String nom;
	private String prenom;
	private String dateNaissance;
	private String dateDeces;
	private String sexe;
	private String paysNaissance;

	public PersonneForm() {
		formatter = new SimpleDateFormat("dd-M-yyyy");
		utilisateurService = new UtilisateurServiceImpl();
	}

	public Personne get() {
		Personne p = new Personne();
		if (id > 0) {
			p.setId(id);
		}
		if (redacteurId > 0) {
			Utilisateur u = utilisateurService.findById(redacteurId);
			p.setRedacteur(u);
		}
		if (nom != null && !nom.isBlank()) {
			p.setNom(nom);
		}
		if (prenom != null && !prenom.isBlank()) {
			p.setPrenom(prenom);
		}
		if (dateNaissance != null && !dateNaissance.isBlank()) {
			try {
				Date d = formatter.parse(dateNaissance);
				p.setDateNaissance(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (dateDeces != null && !dateDeces.isBlank()) {
			try {
				Date d = formatter.parse(dateDeces);
				p.setDateDeces(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (sexe != null && !sexe.isBlank()) {
			p.setSexe("M".equals(sexe) ? SexeEnum.M : SexeEnum.F);
		}
		if (paysNaissance != null && !paysNaissance.isBlank()) {
			p.setPaysNaissance(paysNaissance);
		}
		return p;
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

	public String getDateDeces() {
		return dateDeces;
	}

	public void setDateDeces(String dateDeces) {
		this.dateDeces = dateDeces;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getPaysNaissance() {
		return paysNaissance;
	}

	public void setPaysNaissance(String paysNaissance) {
		this.paysNaissance = paysNaissance;
	}

}
