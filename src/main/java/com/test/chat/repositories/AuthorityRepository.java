package com.test.chat.repositories;

import com.test.chat.models.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long>
{
    Authority getAuthorityByAuthority(String authority);
    boolean existsByAuthority(String authority);
}
