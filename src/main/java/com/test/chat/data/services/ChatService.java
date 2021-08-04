package com.test.chat.data.services;

import com.fasterxml.jackson.annotation.JsonValue;
import com.test.chat.data.View;
import com.test.chat.data.models.Admin;
import com.test.chat.data.models.Chat;
import com.test.chat.data.models.Client;
import com.test.chat.data.repositories.ChatRepository;
import com.test.chat.exceptions.MessageManagerException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService
{
    protected final ChatRepository chatRepository;

    public Chat createChat(@NonNull final Client client)
    {
        return chatRepository.saveAndFlush(new Chat(client));
    }

    public List<Chat> getFreeChats()
    {
        return chatRepository.getChatsByAdminIsNull();
    }

    public Chat getChatByUUID(@NonNull final String chatUUID) throws MessageManagerException {
        return chatRepository.getChatByUuid(chatUUID)
                .orElseThrow(() -> new MessageManagerException("Chat not found"));
    }

    public Chat setAdmin(@NonNull final Chat chat, @NonNull final Admin admin)
    {
        chat.setAdmin(admin);
        return chatRepository.saveAndFlush(chat);
    }

    public List<Chat> getChatByAdminEmail(@NonNull final String email)
    {
        return chatRepository.getChatByAdminEmail(email);
    }
}
