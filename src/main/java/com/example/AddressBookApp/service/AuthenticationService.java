package com.example.AddressBookApp.service;

import com.example.AddressBookApp.dto.AuthUserDTO;
import com.example.AddressBookApp.Interface.IAuthenticationService;
import com.example.AddressBookApp.repository.AuthenticationRepository;
import com.example.AddressBookApp.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    AuthenticationRepository authUserRepository;

    @Override
    public AuthUser register(AuthUserDTO userDTO) throws Exception {
        AuthUser user = new AuthUser(userDTO);

        authUserRepository.save(user);

        return user;
    }


}