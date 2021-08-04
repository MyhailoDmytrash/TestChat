package com.test.chat.security.services;

import com.test.chat.controllers.forms.AuthorizationForm;
import com.test.chat.controllers.forms.RegistrationForm;
import com.test.chat.data.models.Admin;
import com.test.chat.data.services.AdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

// TODO: 03.08.2021 Data validation

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    //protected final Validator validator;
    protected final AdminService adminService;
    protected final JavaWebTokenAuthenticationService javaWebTokenAuthenticationService;

    public void registration(@NonNull final RegistrationForm registrationForm) throws AuthenticationException
    {
        if(!registrationForm.getPassword().equals(registrationForm.getRepeatPassword()))
            throw new AuthenticationException("Passwords don't match");

        //throwExceptionIfDataNotValid(registrationForm);
        adminService.createUser(new Admin(registrationForm));
    }

    public String authorization(@NonNull final AuthorizationForm authorizationForm) throws AuthenticationException
    {
        //throwExceptionIfDataNotValid(authorizationForm);

        return javaWebTokenAuthenticationService
                .createJWT(adminService.getUserByEmailAndPassword(authorizationForm.getEmail(), authorizationForm.getPassword()));
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
