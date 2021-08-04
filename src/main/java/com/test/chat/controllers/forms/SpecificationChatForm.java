package com.test.chat.controllers.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SpecificationChatForm
{
    @NotBlank
    protected String chatUUID;
}