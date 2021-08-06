package com.test.chat.models.dtos;

import com.test.chat.enums.MessageType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class MessageDTO
{
    @NotBlank
    protected String message;

    protected MessageType messageType;

    protected Date sendDate = new Date();

    @NotBlank
    protected String chatUUID;
}
