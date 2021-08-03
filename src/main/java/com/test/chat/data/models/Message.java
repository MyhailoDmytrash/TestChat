package com.test.chat.data.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

// TODO: 03.08.2021 Change MessageType enum to MessageType entity

@Data
@Entity
public class Message extends BaseEntity
{
    @NotBlank
    protected String message;

    protected MessageType messageType;

    @Column(updatable = false)
    protected Date sendData = new Date();

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
