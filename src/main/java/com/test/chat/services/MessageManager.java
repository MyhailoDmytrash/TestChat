package com.test.chat.services;

import com.test.chat.controllers.forms.AskForm;
import com.test.chat.data.models.Chat;
import com.test.chat.data.models.Client;
import com.test.chat.data.services.ChatService;
import com.test.chat.data.services.ClientService;
import com.test.chat.data.services.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageManager
{
    protected final ClientService clientService;
    protected final ChatService chatService;
    protected final MessageService messageService;

    public void sendAsk(@NonNull final AskForm askForm)
    {
        Client client = clientService.getClientByLoginAndUsername(askForm.getLogin(), askForm.getUsername());
        Chat chat = chatService.getChatByClient(client);
        messageService.createMessage(chat, askForm.getMessage());
    }
}
