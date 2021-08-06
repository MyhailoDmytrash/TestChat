package com.test.chat.models.dtos;

import com.test.chat.models.entities.Client;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ChatDTO
{
    protected List<MessageDTO> messages;

    protected ClientDTO client;

    @NotBlank
    protected String uuid;
}
