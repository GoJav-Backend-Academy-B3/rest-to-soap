package com.phincon.wls.controller;

import com.phincon.wls.model.dto.response.jaxb.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<DataResponse<String>> home() {
        return DataResponse.ok("Hello world!!");
    }
}
