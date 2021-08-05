package com.test.chat.services;

import com.test.chat.models.entities.Authority;
import com.test.chat.enums.UserAuthority;
import lombok.NonNull;

public interface AuthorityService
{
    Authority getAuthorityByAuthorityName(@NonNull final UserAuthority userAuthority);
}
