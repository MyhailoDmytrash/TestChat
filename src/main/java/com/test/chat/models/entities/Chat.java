package com.test.chat.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Chat extends BaseEntity
{
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<Message> messages;

    @OneToOne(mappedBy = "chat", fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    protected Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    protected Admin admin;

    @Column(updatable = false)
    protected String uuid;
    public Chat(Client client)
    {
        this.client = client;
    }

    public Chat() {}

    @PrePersist
    public void onCreate()
    {
        uuid = java.util.UUID.randomUUID().toString();
    }
}
