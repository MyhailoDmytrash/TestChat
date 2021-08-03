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

    public Client getClientByLoginAndUsername(@NotBlank final String login, @NotBlank final String username)
    {
        Optional<Client> client = clientRepository.getClientByLogin(login);

        if (client.isEmpty())
            return clientRepository.saveAndFlush(new Client(login, username));

        return client.get();
    }
}
