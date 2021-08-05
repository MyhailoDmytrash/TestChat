package com.test.chat.services;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.MessageManagerException;
import com.test.chat.forms.AnswerForm;
import com.test.chat.forms.AskForm;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Message;
import lombok.NonNull;

import java.util.List;

public interface BrokerService {
    void sendAsk(@NonNull AskForm askForm);

    void catchChat(@NonNull String adminEmail, @NonNull String chatUUID) throws MessageManagerException, AuthenticationException, com.test.chat.exceptions.AuthenticationException;

    List<Chat> getFreeChats();

    List<Chat> getAdminChats(@NonNull String adminEmail);

    Chat getCurrentChat(@NonNull String chatUUIT, @NonNull String adminEmail) throws AuthenticationException, MessageManagerException;

    Message sendAnswer(@NonNull AnswerForm answerForm, @NonNull String adminEmail) throws AuthenticationException, MessageManagerException;
}
