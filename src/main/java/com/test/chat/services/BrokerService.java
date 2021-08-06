package com.test.chat.services;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.forms.AskForm;
import com.test.chat.models.dtos.ChatDTO;
import com.test.chat.models.dtos.MessageDTO;
import lombok.NonNull;

import java.util.List;

public interface BrokerService {
    void sendAsk(@NonNull AskForm askForm);

    void catchChat(@NonNull String adminEmail, @NonNull String chatUUID) throws BrokerServiceException, AuthenticationException;

    List<ChatDTO> getFreeChats();

    List<ChatDTO> getAdminChats(@NonNull String adminEmail);

    ChatDTO getCurrentChat(@NonNull String chatUUIT, @NonNull String adminEmail) throws AuthenticationException, BrokerServiceException;

    MessageDTO sendAnswer(@NonNull MessageDTO messageDTO, @NonNull String adminEmail) throws AuthenticationException, BrokerServiceException;
}
