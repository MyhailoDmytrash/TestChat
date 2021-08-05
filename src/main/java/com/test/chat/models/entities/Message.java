package com.test.chat.models.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.chat.View;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

// TODO: 03.08.2021 Change MessageType enum to MessageType entity

@Data
@Entity
@ToString(of = {"message", "messageType", "sendDate"})
public class Message extends BaseEntity
{
    @JsonView({View.CurrentChat.class, View.OnlyOneMessage.class})
    @NotBlank
    protected String message;

    @JsonView({View.CurrentChat.class, View.OnlyOneMessage.class})
    protected MessageType messageType;

    @JsonView({View.CurrentChat.class, View.OnlyOneMessage.class})
    @Column(updatable = false)
    protected Date sendDate = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    protected Chat chat;

    public Message() {}

    public Message(Chat chat, String message, MessageType messageType)
    {
        this.chat = chat;
        this.message = message;
        this.messageType = messageType;
    }

    public enum MessageType{
        ASK, ANSWER
    }
}
