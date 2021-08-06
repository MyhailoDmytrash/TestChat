package com.test.chat.controllers;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.forms.AskForm;
import com.test.chat.models.dtos.ChatDTO;
import com.test.chat.models.dtos.MessageDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MessageController {
    void postAsk(AskForm askForm);

    List<ChatDTO> getChats();

    void postCatchChat(ChatDTO specificationChatForm,
                       Authentication authentication) throws AuthenticationException, BrokerServiceException;

    List<ChatDTO> getMyChats(Authentication authentication);

    ChatDTO getCurrentChat(ChatDTO specificationChatForm,
                        Authentication authentication) throws AuthenticationException, BrokerServiceException;

    MessageDTO postAnswer(MessageDTO messageDTO,
                       Authentication authentication) throws AuthenticationException, BrokerServiceException;
}
