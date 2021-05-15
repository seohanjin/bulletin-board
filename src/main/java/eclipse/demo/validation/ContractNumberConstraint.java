package eclipse.demo.validation;

import eclipse.demo.exception.ContactNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContractNumberConstraint {
    String message() default "중복된 회원입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
