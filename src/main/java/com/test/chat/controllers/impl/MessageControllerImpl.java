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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {
    protected final BrokerService brokerService;
    protected final ModelMapper modelMapper;

    @Override
    public void postAsk(AskForm askForm)
    {
        brokerService.sendAsk(askForm);
    }

    @Override
    public List<ChatDTO> getChats()
    {
        return brokerService.getFreeChats();
    }

    @Override
    public void postCatchChat(ChatDTO chatDTO,
                              Authentication authentication) throws AuthenticationException, BrokerServiceException {
        brokerService.catchChat(authentication.getName(), chatDTO.getUuid());
    }

    @Override
    public List<ChatDTO> getMyChats(Authentication authentication)
    {
        return brokerService.getAdminChats(authentication.getName());
    }

    @Override
    public ChatDTO getCurrentChat(ChatDTO chatDTO,
                               Authentication authentication) throws AuthenticationException, BrokerServiceException {
        return brokerService.getCurrentChat(chatDTO.getUuid(), authentication.getName());
    }

    @Override
    public MessageDTO postAnswer(MessageDTO messageDTO,
                              Authentication authentication) throws AuthenticationException, BrokerServiceException {
        return brokerService.sendAnswer(messageDTO, authentication.getName());
    }

}
