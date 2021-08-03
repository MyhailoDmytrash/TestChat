package com.test.chat.data.models;

import com.test.chat.controllers.forms.RegistrationForm;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;

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

    public Admin(RegistrationForm registrationForm)
    {
        this.email = registrationForm.getEmail();
        this.name = registrationForm.getName();
        this.surname = registrationForm.getSurname();
        this.password= registrationForm.getPassword();
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
}
