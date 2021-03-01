package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.RegisterForm;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;

    public UserAccountServiceImpl(UserRepository userRepository,
        UserMapper userMapper, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.entityManager = entityManager;
    }

    @Override
    public void saveUser(RegisterForm form){
        userRepository.save(userMapper.registerFormToUserAccount(form));
    }

    @Override
    public List<UserDTO> findAllUserByHibernate(){
        String sql = "SELECT new com.example.demo.dto.UserDTO(e.username, e.email) "
            +"FROM UserAccount e ";
        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }

    @Override
    public List<UserAccount> findAllUserAccount() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        return userRepository.findAll().stream()
            .map(userMapper::userAccountToUserDTO)
            .collect(Collectors.toList());
    }
}
