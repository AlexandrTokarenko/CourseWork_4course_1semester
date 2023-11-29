package com.example.coursework.valid.validator;

import com.example.coursework.valid.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private Pattern pattern = Pattern.compile("^0[0-9]{9}$");

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return pattern.matcher(email).find();
    }
}
