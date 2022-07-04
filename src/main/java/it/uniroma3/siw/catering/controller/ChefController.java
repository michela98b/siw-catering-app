package it.uniroma3.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	ChefService chefService;
	
	@RequestMapping(value = "/chef/{id}", method = RequestMethod.GET)
	public String getChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chef";
	}
	
	@RequestMapping(value = "/chef", method = RequestMethod.GET)
	public String getChefs(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		return "chefs";
	}
	
	
	

}
