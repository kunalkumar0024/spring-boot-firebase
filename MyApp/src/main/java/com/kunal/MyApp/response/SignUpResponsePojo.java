package com.kunal.MyApp.response;

import com.kunal.MyApp.request.pojo.LoginCredentials;
import com.kunal.MyApp.response.pojo.AbstractResponse;
import lombok.Data;

@Data
public class SignUpResponsePojo extends AbstractResponse {
    private String status;
    private String statusDesc;
    private LoginCredentials loginCredentials;
}
