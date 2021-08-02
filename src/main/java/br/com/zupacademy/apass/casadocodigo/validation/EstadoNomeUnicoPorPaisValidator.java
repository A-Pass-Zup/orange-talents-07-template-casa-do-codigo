package br.com.zupacademy.apass.casadocodigo.validation;

import br.com.zupacademy.apass.casadocodigo.dto.request.EstadoRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EstadoNomeUnicoPorPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(EstadoRequestDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        var estadoDto = ((EstadoRequestDto)target);

        var result = entityManager.createQuery("SELECT id FROM Estado WHERE pais.id = :pPaisId AND nome = :pNome")
                .setParameter("pPaisId", ((EstadoRequestDto)target).getPaisId())
                .setParameter("pNome", ((EstadoRequestDto)target).getNome())
                .getResultList();

        if(!result.isEmpty()) {
            errors.rejectValue("nome", "EstadoNomeUnicoPorPais");
        }
    }
}
