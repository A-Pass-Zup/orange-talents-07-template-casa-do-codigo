package br.com.zupacademy.apass.casadocodigo.validation;


import br.com.zupacademy.apass.casadocodigo.dto.request.ClienteRequestDto;
import br.com.zupacademy.apass.casadocodigo.modelo.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

@Component
public class ClientePaisEstadoValidator implements Validator {

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ClienteRequestDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors()) {
            return;
        }

        var clienteDto = (ClienteRequestDto) target;

        if (clienteDto.getEstadoId() == null) {
            var paisTemEstados = !this.entityManager.createQuery("SELECT id FROM Estado WHERE pais.id = :pPaisId")
                    .setParameter("pPaisId", clienteDto.getPaisId())
                    .getResultList()
                    .isEmpty();

            if (paisTemEstados && clienteDto.getEstadoId() == null) {
                errors.rejectValue("estadoId", "PrecisaSelecionarEstado");
            }

        } else {

            var estado = this.entityManager.find(Estado.class, clienteDto.getEstadoId());

            if(estado == null) {
                errors.rejectValue("estadoId", "EstadoNaoExiste");
            } else if (estado.getPais().getId() != clienteDto.getPaisId()) {
                errors.rejectValue("estadoId", "PaisEEstadoNaoVinculados");
            }
        }
    }
}

