package com.test.chat.mappers;

import com.test.chat.models.dtos.AdminDTO;
import com.test.chat.models.entities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AdminMapper
{
    @Mappings({
            @Mapping(target = "id", source = "admin.id"),
            @Mapping(target = "email", source = "admin.email"),
            @Mapping(target = "name", source = "admin.name"),
            @Mapping(target = "surname", source = "admin.surname"),
            @Mapping(target = "password", source = "admin.password")
    })
    AdminDTO adminToAdminDTO(final Admin admin);

    @Mappings({
            @Mapping(target = "id", source = "adminDTO.id"),
            @Mapping(target = "email", source = "adminDTO.email"),
            @Mapping(target = "name", source = "adminDTO.surname"),
            @Mapping(target = "password", source = "adminDTO.password"),
    })
    Admin adminDTOtoAdmin(final AdminDTO adminDTO);
}
