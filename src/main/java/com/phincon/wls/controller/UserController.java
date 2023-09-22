package com.phincon.wls.controller;

import com.phincon.wls.model.dto.request.UserRequest;
import com.phincon.wls.model.dto.response.User;
import com.phincon.wls.model.dto.response.jaxb.DataResponse;
import com.phincon.wls.model.dto.response.jaxb.UserResponse;
import com.phincon.wls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<DataResponse<UserResponse>> getUserDetail(@RequestBody UserRequest userRequest) throws JAXBException {

        UserResponse userResponse = userService.getUser(userRequest.getAcctNbr(), userRequest.getAcctType());

        return DataResponse.ok(userResponse);
    }

    @PostMapping("/user-native")
    public ResponseEntity<DataResponse<User>> getUserDetailNative(@RequestBody UserRequest request) throws Exception {
        return DataResponse.ok(userService.getUserNative(request));
    }
}
