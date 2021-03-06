package com.test.chat.models.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Data
@Entity
public class Authority extends BaseEntity implements GrantedAuthority
{
    protected String authority;

    //@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY)
    //protected Set<User> users;

    public Authority() {}

    public Authority(String authority)
    {
        this.authority = authority;
    }

    @Override
    public String toString()
    {
        return authority;
    }
}
