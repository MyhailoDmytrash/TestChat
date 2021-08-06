package com.test.chat.controllers;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.models.dtos.AdminDTO;

public interface AuthenticationController {
    void postRegistration(AdminDTO adminDTO) throws AuthenticationException;

    String postAuthentication(AdminDTO adminDTO) throws AuthenticationException;
}
