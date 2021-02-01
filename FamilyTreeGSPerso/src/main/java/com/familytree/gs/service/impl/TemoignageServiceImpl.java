package com.familytree.gs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.familytree.gs.model.Temoignage;
import com.familytree.gs.service.TemoignageService;

@Service("temoignageService")
public class TemoignageServiceImpl implements TemoignageService {
	
	private static final AtomicLong counter = new AtomicLong();

	private static List<Temoignage> listeTemoignages = new ArrayList<Temoignage>();	

	@Override
	public List<Temoignage> getTemoignageParPersonne(long personneId) {
		List<Temoignage> temoignages = new ArrayList<Temoignage>();
		for(Temoignage t: listeTemoignages) {
			if (t.getSujet().getId() == personneId) {
				temoignages.add(t);
			}
		}
		return temoignages;
	}
	
	@Override
	public Temoignage findById(long id) {
		return listeTemoignages.get((int) id);
	}

	@Override
	public void save(Temoignage object) {
		object.setId(counter.getAndIncrement());
		listeTemoignages.add(object);
	}

	@Override
	public void update(Temoignage object) {
		int index = listeTemoignages.indexOf(object);
		listeTemoignages.set(index, object);
	}

	@Override
	public void deleteById(long id) {
		listeTemoignages.remove((int) id);
	}

	@Override
	public List<Temoignage> findAll() {
		return listeTemoignages;
	}

	@Override
	public void deleteAll() {
		listeTemoignages.clear();
	}

	@Override
	public boolean isExist(Temoignage object) {
		return object.getId() != 0 && findById(object.getId()) != null;
	}
}
