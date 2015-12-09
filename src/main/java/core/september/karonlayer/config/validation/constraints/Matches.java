package core.september.karonlayer.config.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import core.september.karonlayer.config.validation.constraints.impl.MatchesValidator;


@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
@Documented
public @interface Matches {

  String message() default "{test.config.validation.constraints.matches}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String field();

  String verifyField();
}