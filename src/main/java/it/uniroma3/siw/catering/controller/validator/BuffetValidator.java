package it.uniroma3.siw.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Ingrediente;

@Component
public class BuffetValidator implements Validator {

	   
	@Override
	public void validate(Object o, Errors errors) {
		
		Buffet buffet = (Buffet) o;

		String nome = buffet.getNome().trim();
		String descrizione = buffet.getDescrizione().trim();

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        if (descrizione.isEmpty())
            errors.rejectValue("descrizione", "required");
		if (buffet.getPiatti().isEmpty()) {
			errors.reject("buffet.piatti.empty");
		}
	}


	@Override
	public boolean supports(Class<?> classe) {
		return Ingrediente.class.equals(classe);
	}

}
