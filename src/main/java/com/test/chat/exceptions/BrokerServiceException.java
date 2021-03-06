package com.test.chat.exceptions;

public class BrokerServiceException extends Throwable
{
    public static final String CHAT_HAS_ADMIN = "Chat already has admin";
    public static final String CAN_NOT_READ = "You can't read this chat";
    public static final String CAN_NOT_WRITE = "You can't write in this chat";

    public BrokerServiceException(String message)
    {
        super(message);
    }
}
