package com.test.chat.services;

import com.test.chat.models.entities.Message;
import lombok.NonNull;

public interface SocketIOService
{
    public static final String SEND_ALL_ADMINS = "send_all_admins_event";
    public static final String USERNAME_SOCKET_HEADER = "username";

    void start() throws Exception;

    void stop();

    void sendMessageToUser(@NonNull final Message message);
}
