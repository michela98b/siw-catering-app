package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	BuffetRepository buffetRepository;
	
	public List<Buffet> findAll(){
		List<Buffet> buffets = new ArrayList<Buffet>();
		
		for(Buffet b: this.buffetRepository.findAll()) {
			buffets.add(b);
		}
		
		return buffets;
	}
	
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Buffet buffet) {
		this.buffetRepository.save(buffet);
	}

	@Transactional
	public void deleteById(Long id) {
		this.buffetRepository.deleteById(id);
	}

}
