package com.phincon.wls.service;

import com.phincon.wls.model.dto.response.UserResponse;

import javax.xml.bind.JAXBException;

public interface UserService {
    UserResponse getUser(String accNumber, String accType) throws JAXBException;

}
