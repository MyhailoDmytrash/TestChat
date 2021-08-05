package com.test.chat.services;

import com.test.chat.models.entities.Client;

import javax.validation.constraints.NotBlank;

public interface ClientService {
    Client getClientByLoginAndUsername(@NotBlank String login, @NotBlank String username);
}
