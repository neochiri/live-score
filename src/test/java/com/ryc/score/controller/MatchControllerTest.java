package com.ryc.score.controller;

import com.ryc.score.MatchController;
import com.ryc.score.service.iface.MatchService;
import com.ryc.score.service.iface.ScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MatchController.class)
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatchService matchService;
    @MockBean
    private ScoreService scoreService;

    @Test
    public void getAllMatches() throws Exception {
        given(matchService.getAllMatches()).willReturn(new ArrayList<>());
        this.mockMvc.perform(get("/v01/match"))
                .andExpect(status().isOk());
    }
}
