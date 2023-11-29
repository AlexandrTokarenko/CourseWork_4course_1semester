package com.example.coursework.valid.validator;

import com.example.coursework.valid.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password,String> {

    private Pattern pattern1 = Pattern.compile(".*[a-zA-Z].*");
    private Pattern pattern2 = Pattern.compile(".*[0-9].*");
    private Pattern pattern3 = Pattern.compile("[^\\p{L}\\p{N}]");

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        return pattern1.matcher(password).find() && pattern2.matcher(password).find() && !pattern3.matcher(password).find();
     }
}
