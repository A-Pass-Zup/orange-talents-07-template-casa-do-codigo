package br.com.zupacademy.apass.casadocodigo.validator;

import br.com.zupacademy.apass.casadocodigo.dto.request.CategoriaRequestDto;
import br.com.zupacademy.apass.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoriaNomeUnicoValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaRequestDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object categoriaRequestDto, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        if(categoriaRepository.existsByNome(
                ((CategoriaRequestDto) categoriaRequestDto).getNome())
        ) {
            errors.rejectValue("nome", "categoriaNomeUnico");
        }
    }
}
