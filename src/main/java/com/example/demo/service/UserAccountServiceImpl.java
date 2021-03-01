package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.RegisterForm;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public void saveUser(RegisterForm form){
        userRepository.save(userMapper.registerFormToUserAccount(form));
    }

    @Override
    public List<UserDTO> findAllUserByHibernate(){
//        String sql = "SELECT new " + UserDTO.class.getName()
//            + "(e.username,e.password,e.email) "
//            +"FROM " + UserAccount.class.getName() + " e ";
//        Session session = this.sessionFactory.getCurrentSession();
//        Query query = session.createQuery(sql);
//        return query.setResultTransformer(Transformers.aliasToBean( UserDTO.class)).list();
        return null;
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
