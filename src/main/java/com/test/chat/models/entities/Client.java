package com.test.chat.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client extends BaseEntity
{
    @Column(unique = true)
    protected String login;

    protected String username;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    protected Chat chat;

    public Client() {}

    public Client(String login, String username)
    {
        this.login = login;
        this.username = username;
    }
}
