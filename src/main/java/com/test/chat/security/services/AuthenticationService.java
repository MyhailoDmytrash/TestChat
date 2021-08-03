package com.test.chat.security.services;

import com.test.chat.controllers.forms.AuthorizationForm;
import com.test.chat.controllers.forms.RegistrationForm;
import com.test.chat.data.models.User;
import com.test.chat.data.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;

// TODO: 03.08.2021 Data validation

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    //protected final Validator validator;
    protected final UserService userService;
    protected final JavaWebTokenAuthenticationService javaWebTokenAuthenticationService;

    public void registration(@NonNull final RegistrationForm registrationForm) throws AuthenticationException
    {
        if(!registrationForm.getPassword().equals(registrationForm.getRepeatPassword()))
            throw new AuthenticationException("Passwords don't match");

        //throwExceptionIfDataNotValid(registrationForm);
        userService.createUser(new User(registrationForm));
    }

    public String authorization(@NonNull final AuthorizationForm authorizationForm) throws AuthenticationException
    {
        //throwExceptionIfDataNotValid(authorizationForm);

        return javaWebTokenAuthenticationService
                .createJWT(userService.getUserByEmailAndPassword(authorizationForm.getEmail(), authorizationForm.getPassword()));
    }

//    protected <T> void throwExceptionIfDataNotValid(T form) throws AuthenticationException {
//        Optional<String> message = validator.validate(form).stream()
//                .findFirst()
//                .map(ConstraintViolation::getMessage);
//
//        if(message.isPresent())
//            throw new AuthenticationException(message.get());
//    }
}
