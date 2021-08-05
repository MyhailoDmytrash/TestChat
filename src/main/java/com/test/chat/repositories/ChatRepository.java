package com.test.chat.repositories;

import com.test.chat.models.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>
{
    Optional<Chat> getChatByUuid(String uuid);
    List<Chat> getChatByAdminEmail(String email);



    List<Chat> getChatsByAdminIsNull();
}
