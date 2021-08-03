package com.test.chat.data.services;

import com.test.chat.data.models.User;
import com.test.chat.data.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class UserService
{
    protected final UserRepository userRepository;
    protected final PasswordEncoder passwordEncoder;
    protected final AuthorityService authorityService;

    public void createUser(@NonNull final User user) throws AuthenticationException
    {
        if(userRepository.existsUserByEmail(user.getEmail()))
            throw new AuthenticationException("User already exists");

        user.setAuthority(authorityService.getAuthorityByAuthorityName(AuthorityService.UserAuthority.ADMIN));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public User getUserByEmailAndPassword(@NonNull final String email, @NonNull final String password) throws AuthenticationException
    {
        return userRepository.getUserByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Wrong email or password"));
    }
}

