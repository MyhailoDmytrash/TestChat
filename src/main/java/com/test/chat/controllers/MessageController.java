package com.test.chat.controllers;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.MessageManagerException;
import com.test.chat.forms.AnswerForm;
import com.test.chat.forms.AskForm;
import com.test.chat.forms.SpecificationChatForm;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Message;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface MessageController {
    void postAsk(@Valid @RequestBody AskForm askForm);

    List<Chat> getChats();

    void postCatchChat(@Valid @RequestBody SpecificationChatForm specificationChatForm,
                       Authentication authentication) throws AuthenticationException, MessageManagerException;

    List<Chat> getMyChats(@Valid Authentication authentication);

    Chat getCurrentChat(@Valid @RequestBody SpecificationChatForm specificationChatForm,
                        Authentication authentication) throws AuthenticationException, MessageManagerException;

    Message postAnswer(@Valid @RequestBody AnswerForm answerForm,
                       Authentication authentication) throws AuthenticationException, MessageManagerException;
}
