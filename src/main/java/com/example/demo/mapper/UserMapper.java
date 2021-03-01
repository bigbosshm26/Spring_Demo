package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.model.RegisterForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserAccount registerFormToUserAccount(RegisterForm form);

    @Mapping(source = "username", target = "accountName")
    UserDTO userAccountToUserDTO(UserAccount userAccount);
}
