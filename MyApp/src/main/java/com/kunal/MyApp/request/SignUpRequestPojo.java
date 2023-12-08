package com.kunal.MyApp.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequestPojo {
    @NotBlank(message = "first name must be present")
    private String firstName;
    private String middleName;
    private String lastName;
    @NotBlank(message = "mobile number must be present")
    private String mobileNumber;
    @NotBlank(message = "email id must be present")
    private String email;
    @NotBlank(message = "password must be present")
    private String password;
    private String city;
    private String state;
    @NotBlank(message = "first name must be present")
    private String pinCode;
}
