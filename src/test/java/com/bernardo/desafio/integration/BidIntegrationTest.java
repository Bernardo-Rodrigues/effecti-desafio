package com.bernardo.desafio.integration;

import com.bernardo.desafio.DesafioApplication;
import com.bernardo.desafio.domain.mother.BidMother;
import com.bernardo.desafio.domain.mother.UserMother;
import com.bernardo.desafio.model.dto.BidDto;
import com.bernardo.desafio.model.dto.UserDto;
import com.bernardo.desafio.model.entities.Reading;
import com.bernardo.desafio.repositories.BidRepository;
import com.bernardo.desafio.repositories.ReadingRepository;
import com.bernardo.desafio.services.JwtService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DesafioApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class BidIntegrationTest implements WithAssertions {

    private static final String BID_CONTROLLER_BASE_URL = "/bids";
    private static final String READ_BID_PATH = "/read";
    private String JWT = "";

    @Autowired
    MockMvc mvc;
    @Autowired
    BidRepository bidRepository;
    @Autowired
    ReadingRepository readingRepository;
    @Autowired
    JwtService jwtService;

    @BeforeAll
    void setJWT(){
        JWT = jwtService.generateToken("user-test-1");
    }

    @Test
    void givenARequestToListAllTheBidsWhenTheWebCrawlerIsCompleteThenReturnAllOfThem() throws Exception {
        MockHttpServletResponse response = mvc.perform(
            get(BID_CONTROLLER_BASE_URL)
                    .header("jwt", JWT))
            .andExpect(status().isOk())
            .andReturn().getResponse();

        Integer responseLength = response.getContentAsString().split("\"id\":").length - 1;
        Integer itemsSaved = bidRepository.findAll().size();

        assertThat(responseLength).isEqualTo(itemsSaved);
    }

    @Test
    void givenARequestToReadABidWhenTheBidExistAndUserIsAuthenticatedThenAddTheRegistryInTheDatabase() throws Exception {
        BidDto bidDto = BidMother.getBidDto();
        UserDto userDto = UserMother.getUserDto();

        mvc.perform(
            post(BID_CONTROLLER_BASE_URL + "/" + bidDto.getId() + READ_BID_PATH)
                    .header("jwt", JWT))
            .andExpect(status().isOk())
            .andReturn().getResponse();

        Reading readingSaved = readingRepository.findByUserIdAndBidId(userDto.getId(), bidDto.getId());

        assertThat(readingSaved).isNotNull();
    }
}
