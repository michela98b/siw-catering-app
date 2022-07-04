package it.uniroma3.siw.catering.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.catering.controller.validator.BuffetValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.PiattoService;
import it.uniroma3.siw.catering.util.FileUploadUtil;

@Controller
public class BuffetController {

	@Autowired
	BuffetService buffetService;

	@Autowired
	ChefService chefService;

	@Autowired
	PiattoService piattoService;

	@Autowired
	BuffetValidator buffetValidator;

	@RequestMapping(value="/buffet", method = RequestMethod.GET)
	public String getBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffets";
	}

	@RequestMapping(value="/buffet/{id}", method = RequestMethod.GET)
	public String getBuffet(@PathVariable("id") Long id,Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "buffet";
	}

	@RequestMapping(value="/admin/buffet", method = RequestMethod.POST)
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, @RequestParam("image") MultipartFile multipartFile,
			BindingResult bindingResult, Model model) throws IOException {

		this.buffetValidator.validate(buffet, bindingResult);

		if(!bindingResult.hasErrors()) {
			if(!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				buffet.setPhoto(fileName);

				this.buffetService.save(buffet);

				String uploadDir = "user-photos/" + buffet.getId();

				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

			} else {
				this.buffetService.save(buffet);
			}

			model.addAttribute("buffets", this.buffetService.findAll());
			return "admin/buffets";

		}
		
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("listaPiatti", this.piattoService.findAll());
		return "admin/buffetForm";
	}

	@RequestMapping(value="/admin/buffet", method = RequestMethod.GET)
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("listaPiatti", this.piattoService.findAll());
		model.addAttribute("piatti", new ArrayList<Piatto>());
		return "admin/buffetForm";
	}

	@RequestMapping(value="/admin/buffets", method = RequestMethod.GET)
	public String getAdminBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.findAll());
		return "admin/buffets";
	}

	@RequestMapping(value="/admin/toDeleteBuffet/{id}", method = RequestMethod.GET)
	public String toDeleteBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "admin/toDeleteBuffet";
	}

	@RequestMapping(value="/admin/deleteBuffet/{id}", method = RequestMethod.GET)
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		this.buffetService.deleteById(id);
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/buffets";
	}


}
