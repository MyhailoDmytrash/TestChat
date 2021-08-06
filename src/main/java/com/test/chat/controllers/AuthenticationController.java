package com.test.chat.controllers;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.models.dtos.AdminDTO;
import com.test.chat.transfers.AdminDTOTransfer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {
    @PostMapping("/registration")
    void postRegistration(@Validated(AdminDTOTransfer.Registration.class) @RequestBody AdminDTO adminDTO) throws AuthenticationException;

    @PostMapping("/authorization")
    String postAuthentication(@Validated(AdminDTOTransfer.Login.class) @RequestBody AdminDTO adminDTO) throws AuthenticationException;
}
