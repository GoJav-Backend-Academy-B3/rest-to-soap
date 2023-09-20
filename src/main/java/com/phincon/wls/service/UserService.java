package com.phincon.wls.service;

import com.phincon.wls.model.UserResponse;

public interface UserService {
    UserResponse getUser(String accNumber, String accType);

}
