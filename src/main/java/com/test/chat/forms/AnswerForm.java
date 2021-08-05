package com.test.chat.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AnswerForm extends SpecificationChatForm
{
    @NotBlank
    protected String message;

}
