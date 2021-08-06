package com.test.chat.services;

import com.test.chat.enums.MessageType;
import com.test.chat.models.entities.Chat;
import com.test.chat.models.entities.Message;
import lombok.NonNull;

public interface MessageService {
    Message sendMessage(@NonNull Chat chat, @NonNull String message, @NonNull MessageType messageType);
}
