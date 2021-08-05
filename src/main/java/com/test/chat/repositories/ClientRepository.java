package com.test.chat.repositories;

import com.test.chat.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{
    Optional<Client> getClientByLogin(String login);
}
