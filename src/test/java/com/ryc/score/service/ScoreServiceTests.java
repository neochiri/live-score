package com.ryc.score.service;

import com.ryc.score.mapper.ScoreMapper;
import com.ryc.score.model.Score;
import com.ryc.score.repository.MatchRepository;
import com.ryc.score.repository.ScoreRepository;
import com.ryc.score.service.impl.ScoreServiceImpl;
import com.ryc.score.utils.UtilsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith({MockitoExtension.class})
public class ScoreServiceTests {

    private final String SCORE_MODEL_JSON = "score-model.json";

    @InjectMocks
    private ScoreServiceImpl scoreService;
    private final ScoreRepository scoreRepository;
    private final MatchRepository matchRepository;
    private final ScoreMapper scoreMapper;

    public ScoreServiceTests(@Mock ScoreRepository scoreRepository, @Mock MatchRepository matchRepository, @Mock ScoreMapper scoreMapper){
        this.matchRepository = matchRepository;
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    @Test
    public void testUpdateScoreOK(){
        String matchId = "95b590b0-8b2e-4552-8866-096a25f064ae";
        Score scoreToUpdate = (Score) UtilsTest.getObjectFromJsonFile(SCORE_MODEL_JSON, Score.class);

        Score score = scoreService.updateScore(matchId, scoreToUpdate);

        assertEquals(score.getId(), scoreToUpdate.getId());
        assertEquals(score.getScoreHomeTeam(), 1);
        assertEquals(score.getScoreAwayTeam(), 0);
    }
}
