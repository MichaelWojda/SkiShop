package pl.san.mw.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ FIELD})
@Retention(RUNTIME)
public @interface UniqueName {
    String message() default "Username already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
