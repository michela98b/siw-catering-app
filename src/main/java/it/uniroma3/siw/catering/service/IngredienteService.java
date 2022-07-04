package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;


@Service
public class IngredienteService {

	@Autowired
	IngredienteRepository ingredienteRepository;
	
	public List<Ingrediente> findAll() {
		
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		
		for(Ingrediente i: this.ingredienteRepository.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}
	
	public Ingrediente findById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		this.ingredienteRepository.save(ingrediente);
	}
	
	public boolean alreadyExists(Ingrediente ingrediente) {
		return this.ingredienteRepository.existsByNomeAndDescrizioneAndOrigine(ingrediente.getNome(), ingrediente.getDescrizione() , ingrediente.getOrigine());	
	}
	
}
