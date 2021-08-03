package com.test.chat.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Chat extends BaseEntity
{
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<Message> messages;

    @OneToOne(mappedBy = "chat")
    protected Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    protected Admin admin;

    public Chat() {}

    public Chat(Client client)
    {
        this.client = client;
    }
}
