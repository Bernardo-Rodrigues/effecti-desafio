package com.bernardo.desafio.integration;

import com.bernardo.desafio.domain.mother.UserMother;
import com.bernardo.desafio.model.request.CreateUserRequest;
import com.bernardo.desafio.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest implements WithAssertions {

    private static final String USER_CONTROLLER_BASE_URL = "/users";
    private static final String AUTHENTICATE_USER_PATH = "/authenticate";

    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository userRepository;

    @Test
    void givenRequestsToCreateAndAuthenticateAnUserWhenTheGivenNameNotExistYetThenCreateAndReturnAToken() throws Exception  {
        CreateUserRequest request = UserMother.getUserRegistrationRequest();

        mvc.perform(
            post(USER_CONTROLLER_BASE_URL)
                    .content(new ObjectMapper().writeValueAsString(request).getBytes())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andReturn().getResponse();

        assertThat(userRepository.findByName(request.getName())).isNotNull();

        MockHttpServletResponse response = mvc.perform(
                        post(USER_CONTROLLER_BASE_URL + "/" + request.getName() + AUTHENTICATE_USER_PATH))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("token");
    }
}
