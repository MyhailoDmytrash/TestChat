package com.test.chat.data.services;

import com.test.chat.data.models.Chat;
import com.test.chat.data.models.Message;
import com.test.chat.data.repositories.MessageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService
{
    protected final MessageRepository messageRepository;

    public Message sendMessage(@NonNull final Chat chat, @NonNull final String message, @NonNull final Message.MessageType messageType)
    {
        return messageRepository.saveAndFlush(new Message(chat, message, messageType));
    }
}
