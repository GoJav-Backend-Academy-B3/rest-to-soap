package com.phincon.wls.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.wls.model.dto.request.UserRequest;
import com.phincon.wls.model.dto.response.jackson.User;
import com.phincon.wls.model.dto.response.jaxb.UserResponse;
import com.phincon.wls.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
//@ContextConfiguration(classes = {UserController.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        UserResponse userResponse = new UserResponse();
        userResponse.setAcctNbr("12345");
        userResponse.setAcctType("A");

        User user = new User();
        user.setAcctNbr("12345");
        user.setAcctType("A");

        // Mock the userService's getUser method to return the sample response
        lenient().when(userService.getUser("12345", "A")).thenReturn(userResponse);
        lenient().when(userService.getUserNative(any(UserRequest.class))).thenReturn(user);
    }

    @Test
    public void whenUserControllerInjected_thenNotNull() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void testGetUserDetail() throws Exception {
        // Create a sample user request
        UserRequest userRequest = new UserRequest();
        userRequest.setAcctNbr("12345");
        userRequest.setAcctType("A");

        // Perform the POST request to /v1/user with the sample userRequest
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/user")
                        .content(new ObjectMapper().writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.acct_nbr").value("12345"))
                .andExpect(jsonPath("$.data.acct_type").value("A"));

        // Verify that the userService's getUser method was called with the correct arguments
        verify(userService, times(1)).getUser("12345", "A");
    }

    @Test
    public void testGetUserDetailNative() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setAcctNbr("12345");
        userRequest.setAcctType("A");

        // Perform the POST request to /v1/user with the sample userRequest
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/user-native")
                        .content(new ObjectMapper().writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.acct_nbr").value("12345"))
                .andExpect(jsonPath("$.data.acct_type").value("A"));

        // Verify that the userService's getUser method was called with the correct arguments
        verify(userService, times(1)).getUserNative(any(UserRequest.class));
    }
}
