package com.test.chat.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.chat.ChatApplication;
import com.test.chat.controllers.forms.AnswerForm;
import com.test.chat.controllers.forms.AskForm;
import com.test.chat.controllers.forms.SpecificationChatForm;
import com.test.chat.data.View;
import com.test.chat.data.models.Chat;
import com.test.chat.exceptions.MessageManagerException;
import com.test.chat.services.MessageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Collection;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MessageController
{
    protected final MessageManager messageManager;

    @PostMapping("/message/ask")
    public ResponseEntity<Map<String, String>> postAsk(@RequestBody AskForm askForm)
    {
        messageManager.sendAsk(askForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @JsonView(View.OnlyChats.class)
    @GetMapping("/message/getChats")
    public ResponseEntity<Map<String, Collection<Chat>>> getChats()
    {
        return new ResponseEntity<>(Map.of("chats", messageManager.getFreeChats()), HttpStatus.OK);
    }

    @PostMapping("/message/catchChat")
    public ResponseEntity<Map<String, String>> postCatchChat(@RequestBody SpecificationChatForm specificationChatForm,
                                                             Authentication authentication)
    {
        try
        {
            messageManager.catchChat(authentication.getName(), specificationChatForm.getChatUUID());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Throwable exception)
        {
            return new ResponseEntity<>(Map.of(ChatApplication.ERROR_MESSAGE_NAME, exception.getMessage()),
                    HttpStatus.CONFLICT);
        }
    }

    @JsonView(View.OnlyChats.class)
    @GetMapping("/message/getMyChats")
    public ResponseEntity<Map<String, Collection<Chat>>> getMyChats(Authentication authentication)
    {
        return new ResponseEntity<>(Map.of("chats", messageManager.getAdminChats(authentication.getName())), HttpStatus.OK);
    }

    @JsonView(View.CurrentChat.class)
    @GetMapping("/message/getCurrentChat")
    public ResponseEntity<Map<String, Object>> getCurrentChat(@RequestBody SpecificationChatForm specificationChatForm,
                                                            Authentication authentication)
    {
        try
        {
            return new ResponseEntity<>(Map.of("chat", messageManager.getCurrentChat(specificationChatForm.getChatUUID(),
                    authentication.getName())), HttpStatus.OK);
        }
        catch (Throwable exception)
        {
            return new ResponseEntity<>(Map.of(ChatApplication.ERROR_MESSAGE_NAME, exception.getMessage()),
                    HttpStatus.CONFLICT);
        }
    }

    @JsonView(View.OnlyOneMessage.class)
    @PostMapping("/message/answer")
    public ResponseEntity<Map<String, Object>> postAnswer(@RequestBody AnswerForm answerForm,
                                           Authentication authentication)
    {
        try
        {
            return new ResponseEntity<>(Map.of("message", messageManager.sendAnswer(answerForm, authentication.getName())),
                    HttpStatus.CREATED);
        }
        catch (Throwable exception)
        {
            return new ResponseEntity<>(Map.of(ChatApplication.ERROR_MESSAGE_NAME, exception.getMessage()),
                    HttpStatus.CONFLICT);
        }
    }

}
