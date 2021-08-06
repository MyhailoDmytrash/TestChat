package com.test.chat.services.impl;

import com.test.chat.models.entities.Admin;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Client;
import com.test.chat.repositories.ChatRepository;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.services.ChatService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    protected final ChatRepository chatRepository;

    @Override
    public Chat createChat(@NonNull final Client client)
    {
        return chatRepository.saveAndFlush(new Chat(client));
    }

    @Override
    public List<Chat> getFreeChats()
    {
        return chatRepository.getChatsByAdminIsNull();
    }

    @Override
    public Chat getChatByUUID(@NonNull final String chatUUID) throws BrokerServiceException {
        return chatRepository.getChatByUuid(chatUUID)
                .orElseThrow(() -> new BrokerServiceException("Chat not found"));
    }

    @Override
    public Chat setAdmin(@NonNull final Chat chat, @NonNull final Admin admin)
    {
        chat.setAdmin(admin);
        return chatRepository.saveAndFlush(chat);
    }

    @Override
    public List<Chat> getChatByAdminEmail(@NonNull final String email)
    {
        return chatRepository.getChatByAdminEmail(email);
    }
}
