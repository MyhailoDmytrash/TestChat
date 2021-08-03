package com.test.chat.controllers.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AskForm
{
    @NotBlank
    protected String login;

    @NotBlank
    protected String username;

    @NotBlank
    protected String message;
}
