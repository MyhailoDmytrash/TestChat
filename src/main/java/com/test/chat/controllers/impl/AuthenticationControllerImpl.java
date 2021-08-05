package com.test.chat.controllers.impl;

import com.test.chat.controllers.AuthenticationController;
import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.forms.AuthorizationForm;
import com.test.chat.forms.RegistrationForm;
import com.test.chat.mappers.AdminMapper;
import com.test.chat.models.dtos.AdminDTO;
import com.test.chat.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController
{
    protected final AuthenticationService authenticationService;
    protected final AdminMapper adminMapper;

    @Override
    @PostMapping("/registration")
    public void postRegistration(@Valid @RequestBody AdminDTO adminDTO) throws AuthenticationException {
        authenticationService.registration(adminDTO);
    }

    @Override
    @PostMapping("/authorization")
    public String postAuthentication(@Valid @RequestBody AdminDTO adminDTO) throws AuthenticationException {
        return authenticationService.authorization(adminDTO);
    }
}
