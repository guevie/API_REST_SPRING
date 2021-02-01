package com.familytree.gs.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.familytree.gs.model.Utilisateur;
import com.familytree.gs.service.UtilisateurService;

@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {
	     
	    private static final AtomicLong counter = new AtomicLong();
	     
	    private static List<Utilisateur> utilisateurs;
	     
	    static {
	        utilisateurs = populateDummyUtilisateurs();
	    }
	 
	    public List<Utilisateur> findAll() {
	        return utilisateurs;
	    }
	     
	    public Utilisateur findById(long id) {
	        for(Utilisateur utilisateur : utilisateurs){
	            if(utilisateur.getId() == id){
	                return utilisateur;
	            }
	        }
	        return null;
	    }
	     
	    public Utilisateur findByNom(String nom) {
	        for(Utilisateur utilisateur : utilisateurs){
	            if(utilisateur.getNom().equalsIgnoreCase(nom)){
	                return utilisateur;
	            }
	        }
	        return null;
	    }
	     
	    public void save(Utilisateur utilisateur) {
	        utilisateur.setId(counter.incrementAndGet());
	        utilisateurs.add(utilisateur);
	    }
	 
	    public void update(Utilisateur utilisateur) {
	        int index = utilisateurs.indexOf(utilisateur);
	        utilisateurs.set(index, utilisateur);
	    }
	 
	    public void deleteById(long id) {
	         
	        for (Iterator<Utilisateur> iterator = utilisateurs.iterator(); iterator.hasNext(); ) {
	        	Utilisateur utilisateur = iterator.next();
	            if (utilisateur.getId() == id) {
	                iterator.remove();
	            }
	        }
	    }
	 
	    public boolean isExist(Utilisateur utilisateur) {
	        return findById(utilisateur.getId()) != null;
	    }
	 
	    private static List<Utilisateur> populateDummyUtilisateurs(){
	        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	        Utilisateur u1 = new Utilisateur();
	        u1.setId(counter.incrementAndGet());
	        u1.setNom("DZINGA");
	        u1.setPrenom("Gildas");
	        u1.setEmail("gdzinga@xxx.com");
	        utilisateurs.add(u1);
	        return utilisateurs;
	    }
	 
	    public void deleteAll() {
	        utilisateurs.clear();
	    }
}
