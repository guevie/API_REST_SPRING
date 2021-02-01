package com.familytree.gs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.familytree.gs.model.Epoux;
import com.familytree.gs.model.Personne;
import com.familytree.gs.service.EpouxService;

@Service("epouxService")
public class EpouxServiceImpl implements EpouxService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Epoux> listeEpoux = new ArrayList<Epoux>();	

	public List<Personne> getListeConjoint(long personneId) {
		List<Personne> conjoints = new ArrayList<Personne>();
		for (Epoux epoux: listeEpoux) {
			if (epoux.getSujet().getId() == personneId) {
				conjoints.add(epoux.getConjoint());
			} else if (epoux.getConjoint().getId() == personneId) {
				conjoints.add(epoux.getSujet());
			}
		}
		return conjoints;
	}
	
	@Override
	public Epoux findById(long id) {
		return listeEpoux.get((int) id);
	}

	@Override
	public void save(Epoux object) {
		object.setId(counter.getAndIncrement());
		listeEpoux.add(object);
	}

	@Override
	public void update(Epoux object) {
		int index = listeEpoux.indexOf(object);
		listeEpoux.set(index, object);
	}

	@Override
	public void deleteById(long id) {
		listeEpoux.remove((int) id);
	}

	@Override
	public List<Epoux> findAll() {
		return listeEpoux;
	}

	@Override
	public void deleteAll() {
		listeEpoux.clear();
	}

	@Override
	public boolean isExist(Epoux object) {
		return object.getId() != 0 && findById(object.getId()) != null;
	}

}
