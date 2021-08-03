package com.test.chat.data.models;

import com.test.chat.controllers.forms.AskForm;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client extends BaseEntity
{
    @Column(unique = true)
    protected String login;
    protected String username;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    protected Chat chat;

    public Client() {}

    public Client(String login, String username)
    {
        this.login = login;
        this.username = username;
    }
}
