package com.phincon.wls.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.phincon.wls.model.dto.response.UserResponse;
import com.phincon.wls.model.dto.response.jackson.User;

import javax.xml.bind.JAXBException;

public interface UserService {
    UserResponse getUser(String accNumber, String accType) throws JAXBException;
    User getUserNative(String request) throws Exception;
}
