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

    public void createMessage(@NonNull final Chat chat, @NonNull final String message)
    {
        messageRepository.saveAndFlush(new Message(chat, message, Message.MessageType.ASK));
    }
}
