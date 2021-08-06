package com.test.chat.services.impl;

import com.test.chat.enums.MessageType;
import com.test.chat.exceptions.AuthenticationException;
import com.test.chat.exceptions.BrokerServiceException;
import com.test.chat.forms.AskForm;
import com.test.chat.models.dtos.MessageDTO;
import com.test.chat.models.entities.Admin;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Client;
import com.test.chat.models.entities.Message;
import com.test.chat.services.BrokerService;
import com.test.chat.services.ChatService;
import com.test.chat.services.ClientService;
import com.test.chat.services.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {
    protected final ClientService clientService;
    protected final ChatService chatService;
    protected final MessageService messageService;
    protected final AdminServiceImpl adminService;

    @Override
    public void sendAsk(@NonNull final AskForm askForm)
    {
        Client client = clientService.getClientByLoginAndUsername(askForm.getLogin(), askForm.getUsername());
        messageService.sendMessage(client.getChat(), askForm.getMessage(), MessageType.ASK);
    }

    @Override
    public void catchChat(@NonNull final String adminEmail, @NonNull final String chatUUID) throws BrokerServiceException, AuthenticationException {

        Chat chat = chatService.getChatByUUID(chatUUID);

        if(chat.getAdmin() != null)
            throw new BrokerServiceException(BrokerServiceException.CHAT_HAS_ADMIN);

        chatService.setAdmin(chat, adminService.getUserByEmail(adminEmail));
    }

    @Override
    public List<Chat> getFreeChats()
    {
        return chatService.getFreeChats();
    }

    @Override
    public List<Chat> getAdminChats(@NonNull final String adminEmail)
    {
        return chatService.getChatByAdminEmail(adminEmail);
    }

    @Override
    public Chat getCurrentChat(@NonNull final String chatUUIT, @NonNull final String adminEmail) throws AuthenticationException, BrokerServiceException {
        Admin admin = adminService.getUserByEmail(adminEmail);
        Chat chat = chatService.getChatByUUID(chatUUIT);

        if(chat.getAdmin().equals(admin) || chat.getAdmin() == null)
            return chat;

        throw new BrokerServiceException(BrokerServiceException.CAN_NOT_READ);
    }

    @Override
    public Message sendAnswer(@NonNull final MessageDTO messageDTO, @NonNull final String adminEmail) throws AuthenticationException, BrokerServiceException {
        Admin admin = adminService.getUserByEmail(adminEmail);
        Chat chat = chatService.getChatByUUID(messageDTO.getChatUUID());

        if(chat.getAdmin().equals(admin))
            return messageService.sendMessage(chat, messageDTO.getMessage(), MessageType.ANSWER);

        throw new BrokerServiceException(BrokerServiceException.CAN_NOT_WRITE);
    }
}
