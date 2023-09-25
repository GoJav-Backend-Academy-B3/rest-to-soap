package com.phincon.wls.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.wls.model.dto.request.AccountRequest;
import com.phincon.wls.model.dto.response.jaxb.AccountResponse;
import com.phincon.wls.service.AccountService;
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

@WebMvcTest(AccountController.class)
//@ContextConfiguration(classes = {UserController.class})
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountController userController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAcctNbr("12345");
        accountResponse.setAcctType("A");

        com.phincon.wls.model.dto.response.ntv.AccountResponse user = new com.phincon.wls.model.dto.response.ntv.AccountResponse();
        user.setAcctNbr("12345");
        user.setAcctType("A");

        // Mock the userService's getUser method to return the sample response
        lenient().when(accountService.getAccount("12345", "A")).thenReturn(accountResponse);
        lenient().when(accountService.getAccountNative(any(AccountRequest.class))).thenReturn(user);
    }

    @Test
    public void whenAccountControllerInjected_thenNotNull() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void testGetAccountDetail() throws Exception {
        // Create a sample user request
        AccountRequest userRequest = new AccountRequest();
        userRequest.setAcctNbr("12345");
        userRequest.setAcctType("A");

        // Perform the POST request to /v1/user with the sample userRequest
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/account")
                        .content(new ObjectMapper().writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.acct_nbr").value("12345"))
                .andExpect(jsonPath("$.data.acct_type").value("A"));

        // Verify that the userService's getUser method was called with the correct arguments
        verify(accountService, times(1)).getAccount("12345", "A");
    }

    @Test
    public void testGetAccountDetailNative() throws Exception {
        AccountRequest userRequest = new AccountRequest();
        userRequest.setAcctNbr("12345");
        userRequest.setAcctType("A");

        // Perform the POST request to /v1/user with the sample userRequest
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/account-native")
                        .content(new ObjectMapper().writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data.acct_nbr").value("12345"))
                .andExpect(jsonPath("$.data.acct_type").value("A"));

        // Verify that the userService's getUser method was called with the correct arguments
        verify(accountService, times(1)).getAccountNative(any(AccountRequest.class));
    }
}
