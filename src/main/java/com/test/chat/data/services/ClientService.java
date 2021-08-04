package com.test.chat.data.services;

import com.test.chat.data.models.Client;
import com.test.chat.data.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService
{
    protected final ClientRepository clientRepository;
    protected final ChatService chatService;

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
