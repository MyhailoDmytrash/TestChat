package com.test.chat.exceptions;

public class AuthenticationException extends Throwable
{
    public static final String PASSWORD_DO_NOT_MATCH = "Passwords don't match";
    public static final String USER_EXISTS = "User already exists";
    public static final String WRONG_PASSWORD = "Wrong email or password";

    public AuthenticationException(String message)
    {
        super(message);
    }
}
