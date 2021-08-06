package com.test.chat.controllers.impl;

import com.test.chat.controllers.MessageController;
import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.forms.AskForm;
import com.test.chat.models.dtos.ChatDTO;
import com.test.chat.models.dtos.MessageDTO;
import com.test.chat.services.BrokerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {
    protected final BrokerService brokerService;
    protected final ModelMapper modelMapper;

    @Override
    @PostMapping("/ask")
    public void postAsk(@Valid @RequestBody AskForm askForm)
    {
        brokerService.sendAsk(askForm);
    }

    @Override
    @GetMapping("/getChats")
    public List<ChatDTO> getChats()
    {
        return brokerService.getFreeChats().stream()
                .map(chat -> modelMapper.map(chat, ChatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @PostMapping("/catchChat")
    public void postCatchChat(@Valid @RequestBody ChatDTO chatDTO,
                              Authentication authentication) throws AuthenticationException, BrokerServiceException {
        brokerService.catchChat(authentication.getName(), chatDTO.getUuid());
    }

    @Override
    @GetMapping("/getMyChats")
    public List<ChatDTO> getMyChats(Authentication authentication)
    {
        return brokerService.getAdminChats(authentication.getName())
                .stream().map(chat -> modelMapper.map(chat, ChatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/getCurrentChat")
    public ChatDTO getCurrentChat(@Valid @RequestBody ChatDTO chatDTO,
                               Authentication authentication) throws AuthenticationException, BrokerServiceException {
        return modelMapper.map(brokerService.getCurrentChat(chatDTO.getUuid(),
                authentication.getName()), ChatDTO.class);
    }

    @Override
    @PostMapping("/answer")
    public MessageDTO postAnswer(@Valid @RequestBody MessageDTO messageDTO,
                              Authentication authentication) throws AuthenticationException, BrokerServiceException {
        return modelMapper.map(brokerService.sendAnswer(messageDTO, authentication.getName()), MessageDTO.class);
    }

}
