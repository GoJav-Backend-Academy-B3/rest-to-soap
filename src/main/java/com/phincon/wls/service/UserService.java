package com.phincon.wls.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.phincon.wls.model.UserRequest;
import com.phincon.wls.model.UserResponse;
import com.phincon.wls.model.xml.User;

public interface UserService {
    UserResponse getUser(String accNumber, String accType);
    User getUserNative(String request) throws JsonProcessingException;
}
