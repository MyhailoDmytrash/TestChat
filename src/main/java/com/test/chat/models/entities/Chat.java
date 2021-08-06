package com.test.chat.models.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(of = {"messages"})
public class Chat extends BaseEntity
{
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<Message> messages;

    @OneToOne(mappedBy = "chat", fetch = FetchType.LAZY)
    protected Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    protected Admin admin;

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
