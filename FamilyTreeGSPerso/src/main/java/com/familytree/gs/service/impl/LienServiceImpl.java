package com.familytree.gs.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytree.gs.model.Lien;
import com.familytree.gs.model.Personne;
import com.familytree.gs.service.LienService;

@Service("lienService")
@Transactional
public class LienServiceImpl implements LienService {
	     
	    private static final AtomicLong counter = new AtomicLong();
	     
	    private static List<Lien> liens;
	     
	    static {
	        liens = populateDummyLiens();
	    }
	 
	    private List<Lien> findAllFilteredByPersonneId(long personneId) {
	    	List<Lien> res = new ArrayList<Lien>();
	    	for(Lien lien : liens){
	            if(
	            	lien.getSujet().getId() == personneId 
	            	|| lien.getRelatif().getId() == personneId
	            ){
	                res.add(lien);
	            }
	        }
	        return res;
	    }

	    public List<Personne> getArbre(long personneId, int dimension) {
	    	List<Personne> famille = new ArrayList<Personne>();
	    	if (dimension > 0) {
	    		List<Lien> liensFamiliaux = this.findAllFilteredByPersonneId(personneId);
	    		for (Lien lien: liensFamiliaux) {
	    			Personne sujet = lien.getSujet();
	    			Personne relatif = lien.getRelatif();
	    			if (!famille.contains(sujet)) {
	    				famille.add(sujet);
	    			}
	    			if (!famille.contains(relatif)) {
	    				famille.add(relatif);
	    			}
	    			List<Personne> groupeFamiliale = new ArrayList<Personne>();
    				if (sujet.getId() != personneId) {
    					groupeFamiliale.addAll(this.getArbre(sujet.getId(), dimension - 1));
    				}
    				if (relatif.getId() != personneId) {
    					groupeFamiliale.addAll(this.getArbre(relatif.getId(), dimension - 1));
    				}
    				for (Personne personne : groupeFamiliale) {
    					if (!famille.contains(personne)) {
    						famille.add(personne);
    					}
    				}
	    		}
	    	}
	    	return famille;
	    }
	    
	    public List<Lien> findAll() {
	        return liens;
	    }
	     
	    public Lien findById(long id) {
	        for(Lien lien : liens){
	            if(lien.getId() == id){
	                return lien;
	            }
	        }
	        return null;
	    }
	     
	    public void save(Lien lien) {
	        lien.setId(counter.incrementAndGet());
	        liens.add(lien);
	    }
	 
	    public void update(Lien lien) {
	        int index = liens.indexOf(lien);
	        liens.set(index, lien);
	    }
	 
	    public void deleteById(long id) {
	         
	        for (Iterator<Lien> iterator = liens.iterator(); iterator.hasNext(); ) {
	        	Lien lien = iterator.next();
	            if (lien.getId() == id) {
	                iterator.remove();
	            }
	        }
	    }
	 
	    public boolean isExist(Lien lien) {
	        return lien.getId() != 0 && findById(lien.getId()) != null;
	    }
	 
	    private static List<Lien> populateDummyLiens(){
	        List<Lien> liens = new ArrayList<Lien>();
	        return liens;
	    }
	 
	    public void deleteAll() {
	        liens.clear();
	    }
}
