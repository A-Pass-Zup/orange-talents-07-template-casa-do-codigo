package br.com.zupacademy.apass.casadocodigo.validator;

import br.com.zupacademy.apass.casadocodigo.dto.request.AutorRequestDto;
import br.com.zupacademy.apass.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AutorEmailUnicoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorRequestDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object autorRequestDto, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        if(autorRepository.existsByEmail(
                ((AutorRequestDto)autorRequestDto).getEmail())
        ) {
            errors.rejectValue("email", "autorEmailUnico");
        };
    }
}
