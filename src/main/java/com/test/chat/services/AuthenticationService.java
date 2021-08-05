package com.test.chat.services;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.forms.AuthorizationForm;
import com.test.chat.models.dtos.AdminDTO;
import lombok.NonNull;


public interface AuthenticationService {
    void registration(@NonNull final AdminDTO adminDTO) throws AuthenticationException;

    String authorization(@NonNull final AdminDTO adminDTO) throws AuthenticationException;
}
