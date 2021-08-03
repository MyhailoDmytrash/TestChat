package com.test.chat.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Chat extends BaseEntity
{
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    protected List<Message> messages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    protected User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    protected User admin;
}
