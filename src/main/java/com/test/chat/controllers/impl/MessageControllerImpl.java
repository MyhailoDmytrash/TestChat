package com.test.chat.controllers.impl;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.chat.controllers.MessageController;
import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.forms.AnswerForm;
import com.test.chat.forms.AskForm;
import com.test.chat.forms.SpecificationChatForm;
import com.test.chat.View;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Message;
import com.test.chat.exceptions.MessageManagerException;
import com.test.chat.services.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {
    protected final BrokerService brokerService;

    @Override
    @PostMapping("/ask")
    public void postAsk(@Valid @RequestBody AskForm askForm)
    {
        brokerService.sendAsk(askForm);
    }

    @Override
    @JsonView(View.OnlyChats.class)
    @GetMapping("/getChats")
    public List<Chat> getChats()
    {
        return brokerService.getFreeChats();
    }

    @Override
    @PostMapping("/catchChat")
    public void postCatchChat(@Valid @RequestBody SpecificationChatForm specificationChatForm,
                              Authentication authentication) throws AuthenticationException, MessageManagerException {
        brokerService.catchChat(authentication.getName(), specificationChatForm.getChatUUID());
    }

    @Override
    @JsonView(View.OnlyChats.class)
    @GetMapping("/getMyChats")
    public List<Chat> getMyChats(@Valid Authentication authentication)
    {
        return brokerService.getAdminChats(authentication.getName());
    }

    @Override
    @JsonView(View.CurrentChat.class)
    @GetMapping("/getCurrentChat")
    public Chat getCurrentChat(@Valid @RequestBody SpecificationChatForm specificationChatForm,
                               Authentication authentication) throws AuthenticationException, MessageManagerException {
        return brokerService.getCurrentChat(specificationChatForm.getChatUUID(),
                authentication.getName());
    }

    @Override
    @JsonView(View.OnlyOneMessage.class)
    @PostMapping("/answer")
    public Message postAnswer(@Valid @RequestBody AnswerForm answerForm,
                              Authentication authentication) throws AuthenticationException, MessageManagerException {
        return brokerService.sendAnswer(answerForm, authentication.getName());
    }

}
