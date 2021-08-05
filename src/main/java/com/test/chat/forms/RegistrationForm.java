package com.test.chat.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationForm extends AuthorizationForm
{
    @NotBlank
    protected String name;

    @NotBlank
    protected String surname;

    @NotBlank
    protected String repeatPassword;
}
