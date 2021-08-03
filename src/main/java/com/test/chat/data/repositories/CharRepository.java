package com.test.chat.data.repositories;

import com.test.chat.data.models.Chat;
import com.test.chat.data.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharRepository extends JpaRepository<Chat, Long>
{
    Optional<Chat> getChatByClientLogin(String login);
}
