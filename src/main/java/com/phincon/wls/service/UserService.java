package com.phincon.wls.service;


import com.phincon.wls.model.dto.request.UserRequest;
import com.phincon.wls.model.dto.response.jackson.User;
import com.phincon.wls.model.dto.response.jaxb.UserResponse;

import javax.xml.bind.JAXBException;

public interface UserService {
    UserResponse getUser(String accNumber, String accType) throws JAXBException;
    User getUserNative(UserRequest request) throws Exception;
}
