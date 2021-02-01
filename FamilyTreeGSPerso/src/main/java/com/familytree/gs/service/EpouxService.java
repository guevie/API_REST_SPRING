package com.familytree.gs.service;

import java.util.List;

import com.familytree.gs.model.Epoux;
import com.familytree.gs.model.Personne;

public interface EpouxService extends GenericService<Epoux> {

	List<Personne> getListeConjoint(long personneId);
}
