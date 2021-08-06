package com.test.chat.services.impl;

import com.test.chat.enums.MessageType;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Message;
import com.test.chat.repositories.MessageRepository;
import com.test.chat.services.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService
{
    protected final MessageRepository messageRepository;

    @Override
    public Message sendMessage(@NonNull final Chat chat, @NonNull final String message, @NonNull final MessageType messageType)
    {
        return messageRepository.saveAndFlush(new Message(chat, message, messageType));
    }
}
