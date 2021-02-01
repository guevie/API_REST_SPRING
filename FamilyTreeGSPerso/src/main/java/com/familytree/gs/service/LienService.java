package com.familytree.gs.service;

import java.util.List;

import com.familytree.gs.model.Lien;
import com.familytree.gs.model.Personne;

public interface LienService extends GenericService<Lien> {

	List<Personne> getArbre(long personneId, int dimension);
}
