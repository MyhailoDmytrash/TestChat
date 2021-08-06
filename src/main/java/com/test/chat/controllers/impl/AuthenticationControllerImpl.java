package com.test.chat.controllers.impl;

import com.test.chat.controllers.AuthenticationController;
import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.models.dtos.AdminDTO;
import com.test.chat.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController
{
    protected final AuthenticationService authenticationService;

    @Override
    public void postRegistration(AdminDTO adminDTO) throws AuthenticationException {
        authenticationService.registration(adminDTO);
    }

    @Override
    public String postAuthentication(AdminDTO adminDTO) throws AuthenticationException {
        return authenticationService.authorization(adminDTO);
    }
}
