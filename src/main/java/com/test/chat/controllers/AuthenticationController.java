package com.test.chat.controllers;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.models.dtos.AdminDTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationController {
    void postRegistration(@Valid @RequestBody AdminDTO adminDTO) throws AuthenticationException;

    String postAuthentication(@Valid @RequestBody AdminDTO adminDTO) throws AuthenticationException;
}
