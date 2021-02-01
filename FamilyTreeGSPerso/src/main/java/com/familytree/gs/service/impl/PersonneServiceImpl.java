package com.familytree.gs.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytree.gs.model.Personne;
import com.familytree.gs.model.enums.SexeEnum;
import com.familytree.gs.service.PersonneService;

@Service("personneService")
@Transactional
public class PersonneServiceImpl implements PersonneService {
	     
	    private static final AtomicLong counter = new AtomicLong();
	     
	    private static List<Personne> personnes;
	     
	    static {
	        personnes = populateDummyPersonnes();
	    }
	 
	    public List<Personne> findAll() {
	        return personnes;
	    }
	     
	    public Personne findById(long id) {
	        for(Personne personne : personnes){
	            if(personne.getId() == id){
	                return personne;
	            }
	        }
	        return null;
	    }
	     
	    public Personne findByNom(String nom) {
	        for(Personne personne : personnes){
	            if(personne.getNom().equalsIgnoreCase(nom)){
	                return personne;
	            }
	        }
	        return null;
	    }
	     
	    public void save(Personne personne) {
	        personne.setId(counter.incrementAndGet());
	        personnes.add(personne);
	    }
	 
	    public void update(Personne personne) {
	        int index = personnes.indexOf(personne);
	        personnes.set(index, personne);
	    }
	 
	    public void deleteById(long id) {
	         
	        for (Iterator<Personne> iterator = personnes.iterator(); iterator.hasNext(); ) {
	        	Personne personne = iterator.next();
	            if (personne.getId() == id) {
	                iterator.remove();
	            }
	        }
	    }
	 
	    public boolean isExist(Personne personne) {
	        return findById(personne.getId()) != null;
	    }
	 
	    private static List<Personne> populateDummyPersonnes(){
	        List<Personne> personnes = new ArrayList<Personne>();
	        //
	        Personne p1 = new Personne();
	        p1.setId(counter.incrementAndGet());
	        p1.setNom("DZINGA");
	        p1.setPrenom("Joseph");
	        p1.setSexe(SexeEnum.M);
	        personnes.add(p1);
	        //
	        Personne p2 = new Personne();
	        p2.setId(counter.incrementAndGet());
	        p2.setNom("MOUANZA");
	        p2.setPrenom("Hortense");
	        p2.setSexe(SexeEnum.F);
	        personnes.add(p2);
	        //
	        Personne p3 = new Personne();
	        p3.setId(counter.incrementAndGet());
	        p3.setNom("DZINGA");
	        p3.setPrenom("Damien");
	        p3.setSexe(SexeEnum.M);
	        personnes.add(p3);
	        //
	        Personne p4 = new Personne();
	        p4.setId(counter.incrementAndGet());
	        p4.setNom("DZINGA");
	        p4.setPrenom("Gildas");
	        p4.setSexe(SexeEnum.M);
	        personnes.add(p4);
	        //
	        Personne p5 = new Personne();
	        p5.setId(counter.incrementAndGet());
	        p5.setNom("DZINGA");
	        p5.setPrenom("Emmanuelle");
	        p5.setSexe(SexeEnum.F);
	        personnes.add(p5);
	        //
	        Personne p6 = new Personne();
	        p6.setId(counter.incrementAndGet());
	        p6.setNom("DZINGA");
	        p6.setPrenom("Gabrielle");
	        p6.setSexe(SexeEnum.F);
	        personnes.add(p6);
	       
	        return personnes;
	    }
	 
	    public void deleteAll() {
	        personnes.clear();
	    }
}
