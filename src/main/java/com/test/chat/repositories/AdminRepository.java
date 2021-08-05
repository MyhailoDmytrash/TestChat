package com.test.chat.repositories;

import com.test.chat.models.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>
{
    Optional<Admin> getUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
