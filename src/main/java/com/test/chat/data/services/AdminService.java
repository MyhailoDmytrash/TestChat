package com.test.chat.data.services;

import com.test.chat.data.models.Admin;
import com.test.chat.data.repositories.AdminRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AdminService
{
    protected final AdminRepository adminRepository;
    protected final PasswordEncoder passwordEncoder;
    protected final AuthorityService authorityService;

    public void createUser(@NonNull final Admin admin) throws AuthenticationException
    {
        if(adminRepository.existsUserByEmail(admin.getEmail()))
            throw new AuthenticationException("User already exists");

        admin.setAuthority(authorityService.getAuthorityByAuthorityName(AuthorityService.UserAuthority.ADMIN));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.saveAndFlush(admin);
    }

    public Admin getUserByEmailAndPassword(@NonNull final String email, @NonNull final String password) throws AuthenticationException
    {
        return adminRepository.getUserByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Wrong email or password"));
    }

    public Admin getUserByEmail(@NonNull final String email) throws AuthenticationException {
        return adminRepository.getUserByEmail(email)
                .orElseThrow(() -> new AuthenticationException("User not found"));
    }
}

