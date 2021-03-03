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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private static final Logger LOGGER = LogManager.getLogger(DemoController.class);

    private final UserAccountService userAccountService;

    public DemoController(
        UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/get-all-user-jpa")
    public List<UserDTO> getAllUserByJpa(){
        return userAccountService.findAllUserDTO();
    }

    @GetMapping("/get-all-user-dto-hibernate")
    public List<UserDTO> getAllUserByHibernate(){
        return userAccountService.findAllUserByHibernate();
    }
    @GetMapping("/get-all-user-account")
    public List<UserAccount> getAllUserAccount(){
        return userAccountService.findAllUserAccount();
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestBody @Valid RegisterForm form, BindingResult bindingResult) throws ValidationException {
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
        LOGGER.info("-- before serialization --");
        LOGGER.info(userDTO);
        ObjectMapper om = new ObjectMapper();
        String jsonString = om.writeValueAsString(userDTO);
        LOGGER.info("-- after serialization --");
        LOGGER.info(jsonString);
        return jsonString;
    }

}
