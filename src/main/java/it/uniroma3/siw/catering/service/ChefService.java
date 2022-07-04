package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	ChefRepository chefRepository;
	
	public Chef findById(Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	public List<Chef> findAll(){
		
		List<Chef> chefs = new ArrayList<Chef>();
		for(Chef c: this.chefRepository.findAll()) {
			chefs.add(c);
		}
		
		return chefs;
	}
	

}
