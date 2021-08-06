package com.test.chat.controllers.impl;

import com.test.chat.controllers.AuthenticationController;
import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.models.dtos.AdminDTO;
import com.test.chat.services.AuthenticationService;
import com.test.chat.transfers.AdminDTOTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController
{
    protected final AuthenticationService authenticationService;

    @Override
    @PostMapping("/registration")
    public void postRegistration(@Validated(AdminDTOTransfer.Registration.class) @RequestBody AdminDTO adminDTO) throws AuthenticationException {
        authenticationService.registration(adminDTO);
    }

    @Override
    @PostMapping("/authorization")
    public String postAuthentication(@Validated(AdminDTOTransfer.Login.class) @RequestBody AdminDTO adminDTO) throws AuthenticationException {
        return authenticationService.authorization(adminDTO);
    }
}
