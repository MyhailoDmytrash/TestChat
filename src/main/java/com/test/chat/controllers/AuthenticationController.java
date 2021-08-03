package com.test.chat.controllers;

import com.test.chat.ChatApplication;
import com.test.chat.controllers.forms.AuthorizationForm;
import com.test.chat.controllers.forms.RegistrationForm;
import com.test.chat.security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class AuthenticationController
{
    protected final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> postRegistration(@RequestBody RegistrationForm registrationForm)
    {
        try
        {
            authenticationService.registration(registrationForm);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (AuthenticationException exception)
        {
            return new ResponseEntity<>(Map.of(ChatApplication.ERROR_MESSAGE_NAME, exception.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/authorization")
    public ResponseEntity<Map<String, String>> postAuthentication(@RequestBody AuthorizationForm authorizationForm)
    {
        try
        {
            return new ResponseEntity<>(Map.of(ChatApplication.JWT_NAME, authenticationService.authorization(authorizationForm)), HttpStatus.OK);
        }
        catch (AuthenticationException exception)
        {
            return new ResponseEntity<>(Map.of(ChatApplication.ERROR_MESSAGE_NAME, exception.getMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/test")
    public String test()
    {
        return "OK";
    }
}
