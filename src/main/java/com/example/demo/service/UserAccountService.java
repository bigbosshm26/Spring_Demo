package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.model.RegisterForm;
import java.util.List;

public interface UserAccountService {

    void saveUser(RegisterForm form);
    List<UserAccount> findAllUserAccount();
    List<UserDTO> findAllUserByHibernate();
    List<UserDTO> findAllUserDTO();
}
