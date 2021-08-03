package com.test.chat.data.services;

import com.test.chat.data.models.Authority;
import com.test.chat.data.repositories.AuthorityRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthorityService
{
    protected final AuthorityRepository authorityRepository;

    @PostConstruct
    protected void onInit()
    {
        Arrays.stream(UserAuthority.values()).forEach(authority -> {
            if(!authorityRepository.existsByAuthority(authority.name()))
                authorityRepository.saveAndFlush(new Authority(authority.name()));
        });
    }

    public Authority getAuthorityByAuthorityName(@NonNull final UserAuthority userAuthority)
    {
        return authorityRepository.getAuthorityByAuthority(userAuthority.name());
    }

    enum UserAuthority
    {
        USER, ADMIN
    }
}
