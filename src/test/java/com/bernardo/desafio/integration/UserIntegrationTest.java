package com.bernardo.desafio.integration;

import com.bernardo.desafio.domain.mother.BidMother;
import com.bernardo.desafio.domain.mother.UserMother;
import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIntegrationTest implements WithAssertions {

    private static final String USER_CONTROLLER_BASE_URL = "/users";
    private static final String AUTHENTICATE_USER_PATH = "/authenticate";

    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository userRepository;

    @Test
    void givenARequestToCreateAnUserWhenTheGivenNameNotExistYestThenCreate() throws Exception  {
        CreateUserRequest request = UserMother.getUserRegistrationRequest();

        mvc.perform(
                post(USER_CONTROLLER_BASE_URL)
                    .content(new ObjectMapper().writeValueAsString(request).getBytes())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andReturn().getResponse();

        assertThat(userRepository.findByName(request.getName())).isNotNull();
    }

    @Test
    void givenARequestToAuthenticateAnUserWhenTheUserExistThenReturnTheToken() throws Exception  {
        UserDto userDto = UserMother.getUserDto();

        MockHttpServletResponse response = mvc.perform(
                        post(USER_CONTROLLER_BASE_URL + "/" + userDto.getName() + AUTHENTICATE_USER_PATH))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("token");
    }
}
