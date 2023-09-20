package com.phincon.wls.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.wls.model.DataResponse;
import com.phincon.wls.model.UserRequest;
import com.phincon.wls.model.UserResponse;
import com.phincon.wls.model.xml.User;
import com.phincon.wls.service.UserService;
import com.phincon.wls.utils.UserBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class UserController {
    final String API_SOAP_URL = "http://localhost:8084/soap";

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("soap")
    private RestTemplate restTemplate;

    @PostMapping("/user")
    public ResponseEntity<DataResponse<UserResponse>> getUserDetail(@RequestBody UserRequest userRequest) {


        UserResponse userResponse = userService.getUser(userRequest.getAcctNbr(), userRequest.getAcctType());

        return DataResponse.ok(userResponse);
    }

    @PostMapping("/user-native")
    public ResponseEntity<DataResponse<User>> getUserDetailNative(@RequestBody UserRequest request) throws Exception {
        String result = UserBinding.jsonToSoap(request);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/xml");

        HttpEntity<String> entity = new HttpEntity<>(result, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_SOAP_URL,
                HttpMethod.POST,
                entity,
                String.class);

        User user = userService.getUserNative(responseEntity.getBody());
        return DataResponse.ok(user);
    }
}
