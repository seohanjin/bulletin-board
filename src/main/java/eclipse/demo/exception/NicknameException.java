package eclipse.demo.exception;

import eclipse.demo.validation.DuplicateNickname;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DuplicateNickname.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NicknameException {
    String message() default "중북된 닉네임 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
