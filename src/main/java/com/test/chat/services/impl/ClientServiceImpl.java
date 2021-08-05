package com.test.chat.services.impl;

import com.test.chat.models.entities.Client;
import com.test.chat.repositories.ClientRepository;
import com.test.chat.services.ChatService;
import com.test.chat.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    protected final ClientRepository clientRepository;
    protected final ChatService chatService;

    @Override
    public Client getClientByLoginAndUsername(@NotBlank final String login, @NotBlank final String username)
    {
        Optional<Client> optionalClient = clientRepository.getClientByLogin(login);

        if (optionalClient.isEmpty())
        {
            Client client = clientRepository.saveAndFlush(new Client(login, username));
            client.setChat(chatService.createChat(client));
            clientRepository.saveAndFlush(client);
            return client;
        }

        return optionalClient.get();
    }
}
