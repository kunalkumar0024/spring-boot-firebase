package com.kunal.MyApp.service;

import com.kunal.MyApp.request.SignUpRequestPojo;
import com.kunal.MyApp.response.SignUpResponsePojo;
import org.springframework.http.ResponseEntity;

public interface SignUpService {

    ResponseEntity<SignUpResponsePojo> getSignUp(SignUpRequestPojo signUpRequestPojo);
}
