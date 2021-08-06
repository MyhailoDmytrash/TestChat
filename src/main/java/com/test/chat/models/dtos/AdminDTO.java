package com.test.chat.models.dtos;

import com.test.chat.models.entities.Authority;
import com.test.chat.models.entities.Chat;
import com.test.chat.transfers.AdminDTOTransfer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AdminDTO
{
    protected Authority authority;

    protected List<Chat> chat;

    @NotNull(groups = AdminDTOTransfer.Login.class)
    @Email(groups = AdminDTOTransfer.Login.class)
    protected String email;

    @NotBlank(groups = AdminDTOTransfer.Registration.class)
    protected String name;

    @NotBlank(groups = AdminDTOTransfer.Registration.class)
    protected String surname;

    @NotBlank(groups = AdminDTOTransfer.Login.class)
    protected String password;

    @NotBlank(groups = AdminDTOTransfer.Registration.class)
    protected String repeatPassword;
}
