package com.test.chat.models.entities;

import com.test.chat.models.dtos.AdminDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Admin extends BaseEntity implements UserDetails
{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    protected Authority authority;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Chat> chat;

    @Email
    @Column(unique = true)
    protected String email;

    protected String name;
    protected String surname;
    protected String password;

    public Admin() {}

    public Admin(final AdminDTO adminDTO)
    {
        this.email = adminDTO.getEmail();
        this.name = adminDTO.getName();
        this.surname = adminDTO.getSurname();
        this.password= adminDTO.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id);
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(id);
    }
}
