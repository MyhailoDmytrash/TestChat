package com.test.chat.data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
public class Message extends BaseEntity
{
    @NotBlank
    protected String message;
    protected Date sendData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    protected Chat chat;
}
