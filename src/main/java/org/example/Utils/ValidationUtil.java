package org.example.Utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidationUtil {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> void validate(Class<T> beanType, String propertyName, Object value) {
        Set<ConstraintViolation<T>> violations = validator.validateValue(beanType, propertyName, value);
        if (!violations.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                errorMessages.append("Ошибка: ").append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(errorMessages.toString());
        }
    }

    public static <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                errorMessages.append(violation.getPropertyPath()).append(": ")
                        .append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(errorMessages.toString());
        }
    }
}
