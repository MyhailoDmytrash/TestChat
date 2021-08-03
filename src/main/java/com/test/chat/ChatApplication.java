package com.test.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication
{
    public static final String ERROR_MESSAGE_NAME = "errorMessage";
    public static final String JWT_NAME = "Authentication-Token";

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

}
