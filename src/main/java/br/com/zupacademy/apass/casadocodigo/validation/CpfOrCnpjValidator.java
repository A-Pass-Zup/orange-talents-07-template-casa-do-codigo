package br.com.zupacademy.apass.casadocodigo.validation;

import br.com.zupacademy.apass.casadocodigo.validation.constraints.CpfOrCnpj;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, String> {


    @Override
    public void initialize(CpfOrCnpj constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        // CPF
        Boolean isValid = s.matches("(^\\d{3}.\\d{3}.\\d{3}-\\d{2}$)");

        if(isValid)
            return true;

        // CNPJ
        isValid = s.matches("(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)");

        return isValid;
    }
}
