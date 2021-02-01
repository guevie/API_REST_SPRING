package com.familytree.gs.service;

import java.util.List;

import com.familytree.gs.model.Temoignage;

public interface TemoignageService extends GenericService<Temoignage> {

	List<Temoignage> getTemoignageParPersonne(long id);

}
