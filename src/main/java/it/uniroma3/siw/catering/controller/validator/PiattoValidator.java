package it.uniroma3.siw.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.PiattoService;


@Component
public class PiattoValidator implements Validator {
	
	@Autowired
	PiattoService piattoService;
	
	@Override
    public void validate(Object o, Errors errors) {
		Piatto piatto = (Piatto) o;
		if (this.piattoService.alreadyExists(piatto)) {
			errors.reject("piatto.duplicato");
		}
		if(piatto.getIngredienti().isEmpty()) {
			errors.reject("piatto.ingredienti.empty");
		}
    }


	@Override
    public boolean supports(Class<?> classe) {
        return Piatto.class.equals(classe);
    }
}
