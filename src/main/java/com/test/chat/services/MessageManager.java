package com.test.chat.services;

import com.test.chat.controllers.forms.AnswerForm;
import com.test.chat.controllers.forms.AskForm;
import com.test.chat.data.models.Admin;
import com.test.chat.data.models.Chat;
import com.test.chat.data.models.Client;
import com.test.chat.data.models.Message;
import com.test.chat.data.services.AdminService;
import com.test.chat.data.services.ChatService;
import com.test.chat.data.services.ClientService;
import com.test.chat.data.services.MessageService;
import com.test.chat.exceptions.MessageManagerException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageManager
{
    protected final ClientService clientService;
    protected final ChatService chatService;
    protected final MessageService messageService;
    protected final AdminService adminService;

    public void sendAsk(@NonNull final AskForm askForm)
    {
        Client client = clientService.getClientByLoginAndUsername(askForm.getLogin(), askForm.getUsername());
        messageService.sendMessage(client.getChat(), askForm.getMessage(), Message.MessageType.ASK);
    }

    public void catchChat(@NonNull final String adminEmail, @NonNull final String chatUUID) throws MessageManagerException, AuthenticationException {

        Chat chat = chatService.getChatByUUID(chatUUID);

        if(chat.getAdmin() != null)
            throw new MessageManagerException("Chat already has admin");

        chatService.setAdmin(chat, adminService.getUserByEmail(adminEmail));
    }

    public List<Chat> getFreeChats()
    {
        return chatService.getFreeChats();
    }

    public List<Chat> getAdminChats(@NonNull final String adminEmail)
    {
        return chatService.getChatByAdminEmail(adminEmail);
    }

    public Chat getCurrentChat(@NonNull final String chatUUIT, @NonNull final String adminEmail) throws AuthenticationException, MessageManagerException {
        Admin admin = adminService.getUserByEmail(adminEmail);
        Chat chat = chatService.getChatByUUID(chatUUIT);

        if(chat.getAdmin().equals(admin) || chat.getAdmin() == null)
            return chat;

        throw new MessageManagerException("You can't read this chat");
    }

    public Message sendAnswer(@NonNull final AnswerForm answerForm, @NonNull final String adminEmail) throws AuthenticationException, MessageManagerException {
        Admin admin = adminService.getUserByEmail(adminEmail);
        Chat chat = chatService.getChatByUUID(answerForm.getChatUUID());

        if(chat.getAdmin().equals(admin))
            return messageService.sendMessage(chat, answerForm.getMessage(), Message.MessageType.ANSWER);

        throw new MessageManagerException("You can't write in this chat");
    }
}
