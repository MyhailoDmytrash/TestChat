package com.test.chat.data.repositories;

import com.test.chat.data.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long>
{
    Authority getAuthorityByAuthority(String authority);
    boolean existsByAuthority(String authority);
}
