package com.kunal.MyApp.service.impl;

import com.kunal.MyApp.Entity.UserEntity;
import com.kunal.MyApp.repository.UserEntityRepository;
import com.kunal.MyApp.request.SignUpRequestPojo;
import com.kunal.MyApp.request.pojo.LoginCredentials;
import com.kunal.MyApp.response.SignUpResponsePojo;
import com.kunal.MyApp.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    UserEntityRepository userEntityRepository;
    @Override
    public ResponseEntity<SignUpResponsePojo> getSignUp(SignUpRequestPojo signUpRequestPojo) {
        UserEntity userEntity = new UserEntity();
        SignUpResponsePojo signUpResponsePojo = new SignUpResponsePojo();
        LoginCredentials loginCredentials = new LoginCredentials();

        userEntity.setUserId("100001");
        userEntity.setUsername(signUpRequestPojo.getFirstName() + "" + signUpRequestPojo.getMobileNumber().substring(6,10));
        userEntity.setFirstName(signUpRequestPojo.getFirstName());
        userEntity.setMiddleName(signUpRequestPojo.getMiddleName());
        userEntity.setLastName(signUpRequestPojo.getLastName());
        userEntity.setCity(signUpRequestPojo.getCity());
        userEntity.setState(signUpRequestPojo.getState());
        userEntity.setPinCode(signUpRequestPojo.getPinCode());
        userEntity.setMobileNumber(signUpRequestPojo.getMobileNumber());

        userEntityRepository.save(userEntity);
        if(userEntityRepository.findById(userEntity.getUserId()).isPresent()){
            signUpResponsePojo.setStatus("SUCCESS");
            signUpResponsePojo.setStatusDesc("User Signed Up Successfully");
            loginCredentials.setMessage("Encrypted login data");
            loginCredentials.setUsername(userEntity.getUsername());
            loginCredentials.setPassword(signUpRequestPojo.getPassword());
            signUpResponsePojo.setLoginCredentials(loginCredentials);
        }


        return ResponseEntity.ok(signUpResponsePojo);
    }
}
