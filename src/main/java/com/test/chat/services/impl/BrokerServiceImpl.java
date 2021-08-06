package com.test.chat.services.impl;

import com.test.chat.enums.MessageType;
import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.forms.AskForm;
import com.test.chat.models.dtos.ChatDTO;
import com.test.chat.models.dtos.MessageDTO;
import com.test.chat.models.entities.Admin;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Client;
import com.test.chat.models.entities.Message;
import com.test.chat.services.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {
    protected final ClientService clientService;
    protected final ChatService chatService;
    protected final MessageService messageService;
    protected final AdminServiceImpl adminService;
    protected final ModelMapper modelMapper;
    protected final SocketIOService socketIOService;

    @Override
    public void sendAsk(@NonNull final AskForm askForm)
    {
        Client client = clientService.getClientByLoginAndUsername(askForm.getLogin(), askForm.getUsername());
        Message message = messageService.sendMessage(client.getChat(), askForm.getMessage(), MessageType.ASK);
        socketIOService.sendMessageToUser(message);
    }

    @Override
    public void catchChat(@NonNull final String adminEmail, @NonNull final String chatUUID) throws BrokerServiceException, AuthenticationException {

        Chat chat = chatService.getChatByUUID(chatUUID);

        if(chat.getAdmin() != null)
            throw new BrokerServiceException(BrokerServiceException.CHAT_HAS_ADMIN);

        chatService.setAdmin(chat, adminService.getUserByEmail(adminEmail));
    }

    @Override
    public List<ChatDTO> getFreeChats()
    {
        return chatService.getFreeChats().stream()
                .map(chat -> modelMapper.map(chat, ChatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatDTO> getAdminChats(@NonNull final String adminEmail)
    {
        return chatService.getChatByAdminEmail(adminEmail).stream()
                .map(chat -> modelMapper.map(chat, ChatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChatDTO getCurrentChat(@NonNull final String chatUUIT, @NonNull final String adminEmail) throws AuthenticationException, BrokerServiceException {
        Admin admin = adminService.getUserByEmail(adminEmail);
        Chat chat = chatService.getChatByUUID(chatUUIT);

        if(chat.getAdmin().equals(admin) || chat.getAdmin() == null)
            return modelMapper.map(chat, ChatDTO.class);

        throw new BrokerServiceException(BrokerServiceException.CAN_NOT_READ);
    }

    @Override
    public MessageDTO sendAnswer(@NonNull final MessageDTO messageDTO, @NonNull final String adminEmail) throws AuthenticationException, BrokerServiceException {
        Admin admin = adminService.getUserByEmail(adminEmail);
        Chat chat = chatService.getChatByUUID(messageDTO.getChatUUID());

        if(chat.getAdmin().equals(admin))
            return modelMapper.map(messageService.sendMessage(chat, messageDTO.getMessage(), MessageType.ANSWER),
                    MessageDTO.class);

        throw new BrokerServiceException(BrokerServiceException.CAN_NOT_WRITE);
    }
}
