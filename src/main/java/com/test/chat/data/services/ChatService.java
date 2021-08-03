package com.test.chat.data.services;

import com.test.chat.data.models.Chat;
import com.test.chat.data.models.Client;
import com.test.chat.data.repositories.CharRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService
{
    protected final CharRepository charRepository;

    public Chat getChatByClient(@NonNull final Client client)
    {
        Optional<Chat> optionalChat = charRepository.getChatByClientLogin(client.getLogin());

        if(optionalChat.isEmpty())
        {
            Chat chat = charRepository.saveAndFlush(new Chat(client));
            client.setChat(chat);

            return chat;
        }

        return optionalChat.get();
    }
}
