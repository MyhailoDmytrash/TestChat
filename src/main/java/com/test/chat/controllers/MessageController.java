package com.test.chat.controllers;

import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.forms.AskForm;
import com.test.chat.models.dtos.ChatDTO;
import com.test.chat.models.dtos.MessageDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public interface MessageController {
    @PostMapping("/ask")
    void postAsk(@Valid @RequestBody AskForm askForm);

    @GetMapping("/getChats")
    List<ChatDTO> getChats();

    @PostMapping("/catchChat")
    void postCatchChat(@Valid @RequestBody ChatDTO chatDTO,
                              Authentication authentication) throws AuthenticationException, BrokerServiceException;

    @GetMapping("/getMyChats")
    List<ChatDTO> getMyChats(Authentication authentication);

    @GetMapping("/getCurrentChat")
    ChatDTO getCurrentChat(@Valid @RequestBody ChatDTO chatDTO,
                                  Authentication authentication) throws AuthenticationException, BrokerServiceException;

    @PostMapping("/answer")
    MessageDTO postAnswer(@Valid @RequestBody MessageDTO messageDTO,
                                 Authentication authentication) throws AuthenticationException, BrokerServiceException;
}
