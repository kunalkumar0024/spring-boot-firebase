package com.kunal.MyApp.controller;

import com.kunal.MyApp.request.SignUpRequestPojo;
import com.kunal.MyApp.response.pojo.AbstractResponse;
import com.kunal.MyApp.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/myApp")
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<? extends AbstractResponse> doSignUp(@RequestBody @Valid SignUpRequestPojo signUpRequestPojo){

        return signUpService.getSignUp(signUpRequestPojo);
    }
}
