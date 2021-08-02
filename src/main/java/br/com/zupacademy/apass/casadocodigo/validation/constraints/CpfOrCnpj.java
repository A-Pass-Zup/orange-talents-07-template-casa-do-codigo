package br.com.zupacademy.apass.casadocodigo.validation.constraints;

import br.com.zupacademy.apass.casadocodigo.validation.CpfOrCnpjValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfOrCnpjValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfOrCnpj {
    String message() default "{br.com.zupacademy.apass.casadocodigo.validation.constraint.cpforcnpj}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
