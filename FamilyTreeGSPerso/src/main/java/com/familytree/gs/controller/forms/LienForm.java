package com.familytree.gs.controller.forms;

import com.familytree.gs.model.Lien;
import com.familytree.gs.model.Personne;
import com.familytree.gs.model.Utilisateur;
import com.familytree.gs.model.enums.StatusLienEnum;
import com.familytree.gs.model.enums.TypeRelationEnum;
import com.familytree.gs.service.PersonneService;
import com.familytree.gs.service.UtilisateurService;
import com.familytree.gs.service.impl.PersonneServiceImpl;
import com.familytree.gs.service.impl.UtilisateurServiceImpl;

public class LienForm {
	
	private UtilisateurService utilisateurService;
	private PersonneService personneService;

	private Long id;
	private Long redacteurId;
	private Long sujetId;
	private Long relatifId;
	private String typeRelation;
	private String status;
	
	public LienForm() {
		utilisateurService = new UtilisateurServiceImpl();
		personneService = new PersonneServiceImpl();
	}
	
	public Lien get() {
		Lien lien = new Lien();
		if (id != null && id > 0) {
			lien.setId(id);
		}
		
		if (redacteurId != null && redacteurId > 0) {
			Utilisateur u = utilisateurService.findById(redacteurId);
			lien.setRedacteur(u);
		}
		
		if (sujetId != null && sujetId > 0) {
			Personne p = personneService.findById(sujetId);
			lien.setSujet(p);
		}
		
		if (relatifId != null && relatifId > 0) {
			Personne p = personneService.findById(relatifId);
			lien.setRelatif(p);
		}
		
		if (typeRelation != null && !typeRelation.isBlank()) {
			TypeRelationEnum tr = null;
			switch(typeRelation) {
			case "PERE":
				tr = TypeRelationEnum.PERE;
				break;

			case "MERE":
				tr = TypeRelationEnum.MERE;
				break;

			case "PERE_ADOPTIF":
				tr = TypeRelationEnum.PERE_ADOPTIF;
				break;

			case "MERE_ADOPTIVE":
				tr = TypeRelationEnum.MERE_ADOPTIVE;
				break;
			}
			lien.setTypeRelation(tr);
		}
		
		if (status != null && !status.isBlank()) {
			StatusLienEnum sl = null;
			switch(status) {
			case "DRAFT":
				sl = StatusLienEnum.DRAFT;
				break;

			case "PUBLIE":
				sl = StatusLienEnum.PUBLIE;
				break;

			case "VALIDE":
				sl = StatusLienEnum.VALIDE;
				break;
			}
			lien.setStatus(sl);
		}
		
		return lien;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRedacteurId() {
		return redacteurId;
	}

	public void setRedacteurId(Long redacteurId) {
		this.redacteurId = redacteurId;
	}

	public Long getSujetId() {
		return sujetId;
	}

	public void setSujetId(Long sujetId) {
		this.sujetId = sujetId;
	}

	public Long getRelatifId() {
		return relatifId;
	}

	public void setRelatifId(Long relatifId) {
		this.relatifId = relatifId;
	}

	public String getTypeRelation() {
		return typeRelation;
	}

	public void setTypeRelation(String typeRelation) {
		this.typeRelation = typeRelation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
