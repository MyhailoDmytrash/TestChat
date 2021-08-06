package com.test.chat.services;

import com.test.chat.models.entities.Admin;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Client;
import com.test.chat.exceptions.BrokerServiceException;
import lombok.NonNull;

import java.util.List;

public interface ChatService
{
    Chat createChat(@NonNull Client client);
    List<Chat> getFreeChats();
    Chat getChatByUUID(@NonNull String chatUUID) throws BrokerServiceException;
    Chat setAdmin(@NonNull Chat chat, @NonNull Admin admin);
    List<Chat> getChatByAdminEmail(@NonNull String email);
}
