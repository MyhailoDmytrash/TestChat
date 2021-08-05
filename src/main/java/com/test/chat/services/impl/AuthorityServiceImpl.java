package com.test.chat.services.impl;

import com.test.chat.models.entities.Authority;
import com.test.chat.repositories.AuthorityRepository;
import com.test.chat.enums.UserAuthority;
import com.test.chat.services.AuthorityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService
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
}
