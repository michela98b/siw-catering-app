package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.catering.controller.validator.IngredienteValidator;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.service.IngredienteService;

@Controller
public class IngredienteController {
	
	@Autowired
	IngredienteService ingredienteService;
	
	@Autowired
	IngredienteValidator ingredienteValidator;
	

	@RequestMapping(value="/admin/ingredienti", method = RequestMethod.GET)
	public String getIngredienti(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "admin/ingredienti";
	}
	
	@RequestMapping(value="/admin/ingrediente", method = RequestMethod.GET)
	public String addIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/ingredienteForm";
	}
	
	@RequestMapping(value="/admin/ingrediente", method = RequestMethod.POST)
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.ingredienteService.save(ingrediente);
			model.addAttribute("ingredienti", this.ingredienteService.findAll());
			return "admin/ingredienti";
		} else {
			return "admin/ingredienteForm";
		}
	}
	
}
