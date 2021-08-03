package com.test.chat.data.repositories;

import com.test.chat.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> getUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
