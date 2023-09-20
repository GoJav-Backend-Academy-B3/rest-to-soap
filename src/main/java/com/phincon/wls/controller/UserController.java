package com.phincon.wls.controller;

import com.phincon.wls.model.DataResponse;
import com.phincon.wls.model.UserRequest;
import com.phincon.wls.model.UserResponse;
import com.phincon.wls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<DataResponse<UserResponse>> getUserDetail(@RequestBody UserRequest userRequest) {


        UserResponse userResponse = userService.getUser(userRequest.getAcctNbr(), userRequest.getAcctType());

        return DataResponse.ok(userResponse);
    }

}
