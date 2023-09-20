package com.phincon.wls.controller;

import com.phincon.wls.model.dto.request.UserRequest;
import com.phincon.wls.model.dto.response.DataResponse;
import com.phincon.wls.model.dto.response.UserResponse;
import com.phincon.wls.model.dto.response.jackson.User;
import com.phincon.wls.service.UserService;
import com.phincon.wls.utils.UserBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;

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
    public ResponseEntity<DataResponse<UserResponse>> getUserDetail(@RequestBody UserRequest userRequest) throws JAXBException {

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
