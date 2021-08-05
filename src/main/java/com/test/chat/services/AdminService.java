package com.test.chat.services;

import com.test.chat.models.entities.Admin;
import lombok.NonNull;

import javax.naming.AuthenticationException;

public interface AdminService
{
    void createUser(@NonNull final Admin admin) throws AuthenticationException;
    Admin getUserByEmailAndPassword(@NonNull final String email, @NonNull final String password) throws AuthenticationException;
    Admin getUserByEmail(@NonNull final String email) throws AuthenticationException;
}
