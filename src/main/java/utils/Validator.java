package utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Validator {

    private javax.validation.Validator validator;

    public Validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public String validate(Object obj) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        if (constraintViolations != null) {
            return constraintViolations.toString();
        }
        return null;
    }

    public String validate(Object obj, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
        if (constraintViolations != null) {
            return constraintViolations.toString();
        }
        return null;
    }
}
