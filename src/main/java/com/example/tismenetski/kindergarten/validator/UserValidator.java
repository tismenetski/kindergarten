package com.example.tismenetski.kindergarten.validator;

import com.example.tismenetski.kindergarten.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


//Annotated with Component , this class responsible for input validation
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        if (user.getPassword().length()<6)
        {
            errors.rejectValue("password","Length","Password must be at least 6 characters");
        }
        if (!user.getPassword().equals(user.getConfirmPassword()))
        {
            errors.rejectValue("confirmPassword","Match","Password must match");
        }
    }
}
