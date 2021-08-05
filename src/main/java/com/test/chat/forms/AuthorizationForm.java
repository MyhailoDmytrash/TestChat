package com.test.chat.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AuthorizationForm
{
    @Email
    protected String email;

    @NotBlank
    protected String password;
}
