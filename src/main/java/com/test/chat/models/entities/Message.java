package com.test.chat.models.entities;

//import com.test.chat.enums.MessageType;
import com.test.chat.enums.MessageType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Entity
public class Message extends BaseEntity
{
    @NotBlank
    protected String message;

    protected MessageType messageType;

    @Column(updatable = false)
    protected Date sendDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    protected Chat chat;

    public Message() {}

    public Message(Chat chat, String message, MessageType messageType)
    {
        this.chat = chat;
        this.message = message;
        this.messageType = messageType;
    }

}
