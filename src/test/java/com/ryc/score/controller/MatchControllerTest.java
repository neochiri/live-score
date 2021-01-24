package com.ryc.score.controller;

import com.ryc.score.MatchController;
import com.ryc.score.model.Match;
import com.ryc.score.service.iface.MatchService;
import com.ryc.score.service.iface.ScoreService;
import com.ryc.score.utils.UtilsTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MatchController.class)
public class MatchControllerTest {

    private final String MATCH_MODEL_JSON = "match-model.json";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MatchService matchService;
    @MockBean
    private ScoreService scoreService;

    @Test
    public void testGetAllMatches() throws Exception {
        given(matchService.getAllMatches()).willReturn(new ArrayList<>());
        this.mockMvc.perform(get("/v01/match"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMatchStatus() throws Exception {
        Match matchUpdated = (Match) UtilsTest.getObjectFromJsonFile(MATCH_MODEL_JSON, Match.class);
        given(matchService.updateStatusMatch(Mockito.anyString(), Mockito.anyString())).willReturn(matchUpdated);

        this.mockMvc.perform(patch("/v01/match/95b590b0-8b2e-4552-8866-096a25f064ae/FINISHED"))
                .andExpect(status().isOk());
    }
}
