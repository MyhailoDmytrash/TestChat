package com.test.chat.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SpecificationChatForm
{
    @NotBlank
    protected String chatUUID;
}
