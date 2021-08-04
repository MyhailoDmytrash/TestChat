package com.test.chat.data.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.chat.data.View;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(of = {"messages"})
public class Chat extends BaseEntity
{
    @JsonView(View.CurrentChat.class)
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<Message> messages;

    @JsonView(View.OnlyChats.class)
    @OneToOne(mappedBy = "chat", fetch = FetchType.LAZY)
    protected Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    protected Admin admin;

    @JsonView(View.OnlyChats.class)
    @Column(updatable = false)
    protected String uuid;

    public Chat() {}

    public Chat(Client client)
    {
        this.client = client;
    }

    @PrePersist
    public void onCreate()
    {
        uuid = java.util.UUID.randomUUID().toString();
    }
}
