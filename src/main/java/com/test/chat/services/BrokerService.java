package com.test.chat.services;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.forms.AskForm;
import com.test.chat.models.dtos.MessageDTO;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Message;
import lombok.NonNull;

import java.util.List;

public interface BrokerService {
    void sendAsk(@NonNull AskForm askForm);

    void catchChat(@NonNull String adminEmail, @NonNull String chatUUID) throws BrokerServiceException, AuthenticationException, com.test.chat.exceptions.AuthenticationException;

    List<Chat> getFreeChats();

    List<Chat> getAdminChats(@NonNull String adminEmail);

    Chat getCurrentChat(@NonNull String chatUUIT, @NonNull String adminEmail) throws AuthenticationException, BrokerServiceException;

    Message sendAnswer(@NonNull MessageDTO messageDTO, @NonNull String adminEmail) throws AuthenticationException, BrokerServiceException;
}
