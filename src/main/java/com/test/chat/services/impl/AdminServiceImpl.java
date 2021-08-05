package com.test.chat.services.impl;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.models.entities.Admin;
import com.test.chat.repositories.AdminRepository;
import com.test.chat.enums.UserAuthority;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AdminServiceImpl
{
    protected final AdminRepository adminRepository;
    protected final PasswordEncoder passwordEncoder;
    protected final AuthorityServiceImpl authorityService;

    public void createUser(@NonNull final Admin admin) throws AuthenticationException
    {
        if(adminRepository.existsUserByEmail(admin.getEmail()))
            throw new AuthenticationException(AuthenticationException.USER_EXISTS);

        admin.setAuthority(authorityService.getAuthorityByAuthorityName(UserAuthority.ADMIN));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.saveAndFlush(admin);
    }

    public Admin getUserByEmailAndPassword(@NonNull final String email, @NonNull final String password) throws AuthenticationException
    {
        return adminRepository.getUserByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new AuthenticationException(AuthenticationException.WRONG_PASSWORD));
    }

    public Admin getUserByEmail(@NonNull final String email) throws AuthenticationException {
        return adminRepository.getUserByEmail(email)
                .orElseThrow(() -> new AuthenticationException("User not found"));
    }
}

