package com.phincon.wls.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.phincon.wls.model.UserResponse;
import com.phincon.wls.model.xml.Soap;
import com.phincon.wls.model.xml.User;
import com.phincon.wls.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResponse getUser(String accNumber, String accType) {


        return null;
    }

    @Override
    public User getUserNative(String request) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        Soap result = xmlMapper.readValue(request, Soap.class);

        return result.getBody().getDataResponse().getUser();
    }
}
