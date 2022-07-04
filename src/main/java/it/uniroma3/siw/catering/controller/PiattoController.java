package it.uniroma3.siw.catering.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.catering.controller.validator.PiattoValidator;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	PiattoService piattoService;
	
	@Autowired
	IngredienteService ingredienteService;
	
	@Autowired
	PiattoValidator piattoValidator;
	
	@RequestMapping(value = "/piatto/{id}", method = RequestMethod.GET)
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		return "piatto";
	}
	
	@RequestMapping(value = "/admin/piatto", method = RequestMethod.GET)
	public String addPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("listaIngredienti", this.ingredienteService.findAll());
		model.addAttribute("ingredienti", new ArrayList<Ingrediente>());
		return "admin/piattoForm";
	}
	
	@RequestMapping(value = "/admin/piatto", method = RequestMethod.POST)
	public String savePiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		
		this.piattoValidator.validate(piatto, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "piatto";
		}
		else {
			model.addAttribute("listaIngredienti", this.ingredienteService.findAll());
			return "admin/piattoForm";
		}
	}
}
