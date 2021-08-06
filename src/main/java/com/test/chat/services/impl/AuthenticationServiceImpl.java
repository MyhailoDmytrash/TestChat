package com.test.chat.services.impl;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.models.dtos.AdminDTO;
import com.test.chat.models.entities.Admin;
import com.test.chat.services.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// TODO: 03.08.2021 Data validation

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    protected final AdminServiceImpl adminService;
    protected final JavaWebTokenAuthenticationServiceImpl javaWebTokenAuthenticationService;

    @Override
    public void registration(@NonNull final AdminDTO adminDTO) throws AuthenticationException
    {
        if(!adminDTO.getPassword().equals(adminDTO.getRepeatPassword()))
            throw new AuthenticationException(AuthenticationException.PASSWORD_DO_NOT_MATCH);

        adminService.createUser(new Admin(adminDTO));
    }

    @Override
    public String authorization(@NonNull final AdminDTO adminDTO) throws AuthenticationException
    {
        return javaWebTokenAuthenticationService
                .createJWT(adminService.getUserByEmailAndPassword(adminDTO.getEmail(), adminDTO.getPassword()));
    }
}
