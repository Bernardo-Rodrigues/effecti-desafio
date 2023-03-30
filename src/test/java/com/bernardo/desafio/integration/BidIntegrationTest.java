package com.bernardo.desafio.integration;

import com.bernardo.desafio.repositories.BidRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BidIntegrationTest implements WithAssertions {

    private static final String BID_CONTROLLER_BASE_URL = "/bids";

    @Autowired
    MockMvc mvc;
    @Autowired
    BidRepository bidRepository;

    @Test
    void givenARequestToListAllTheBidsWhenTheWebCrawlerIsCompleteThenReturnAllOfThem() throws Exception {
        MockHttpServletResponse response = mvc.perform(get(BID_CONTROLLER_BASE_URL))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Integer responseLength = response.getContentAsString().split("\"id\":").length - 1;
        Integer itemsSaved = bidRepository.findAll().size();

        assertThat(responseLength).isEqualTo(itemsSaved);
    }
}
