package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.RegisterForm;
import com.example.demo.service.UserAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/get-all-user-jpa")
    public List<UserDTO> getAllUserByJpa(){
        return userAccountService.findAllUserDTO();
    }

//    @GetMapping("/get-all-user-dto-hibernate")
//    public List<UserDTO> getAllUserByHibernate(){
//        return userAccountService.findAllUserByHibernate();
//    }
    @GetMapping("/get-all-user-account")
    public List<UserAccount> getAllUserAccount(){
        return userAccountService.findAllUserAccount();
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestBody @Valid RegisterForm form, BindingResult bindingResult) throws Exception {
        if(!form.getPassword().equals(form.getPasswordConfirm())){
            return "Password confirm ko trung password!";
        }
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        userAccountService.saveUser(form);
        return "Successful!";
    }

    @PostMapping("/test-Json")
    public String testJson(@RequestBody UserDTO userDTO) throws JsonProcessingException {
        System.out.println("-- before serialization --");
        System.out.println(userDTO);
        ObjectMapper om = new ObjectMapper();
        String jsonString = om.writeValueAsString(userDTO);
        System.out.println("-- after serialization --");
        System.out.println(jsonString);
        return jsonString;
    }
}
