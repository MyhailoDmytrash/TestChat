package com.test.chat.models.dtos;

import com.test.chat.models.entities.Authority;
import com.test.chat.models.entities.Chat;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AdminDTO
{
    protected Authority authority;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Chat> chat;

    @Email
    protected String email;

    @NotBlank
    protected String name;

    @NotBlank
    protected String surname;

    @NotBlank
    protected String password;

    @NotBlank
    protected String repeatPassword;
}
