package com.ryc.score.service;

import com.ryc.score.entity.MatchEntity;
import com.ryc.score.mapper.MatchMapper;
import com.ryc.score.model.Match;
import com.ryc.score.model.MatchStatus;
import com.ryc.score.repository.MatchRepository;
import com.ryc.score.service.iface.MatchService;
import com.ryc.score.service.impl.MatchServiceImpl;
import com.ryc.score.utils.UtilsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({MockitoExtension.class})
public class MatchServiceTests {

    private final String MATCH_MODEL_JSON = "match-model.json";
    private final String MATCH_ENTITY_JSON = "match-entity.json";

    @InjectMocks
    private MatchServiceImpl matchService;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchServiceTests(@Mock MatchRepository matchRepository, @Mock MatchMapper matchMapper){
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    @Test
    public void testStartMatchOK(){
        String matchId = "95b590b0-8b2e-4552-8866-096a25f064ae";
        String matchStatus = "RUNNING";
        MatchEntity matchEntityFound = (MatchEntity) UtilsTest.getObjectFromJsonFile(MATCH_ENTITY_JSON, MatchEntity.class);
        MatchEntity matchEntityUpdated = (MatchEntity) UtilsTest.getObjectFromJsonFile(MATCH_ENTITY_JSON, MatchEntity.class);
        matchEntityUpdated.setStatus(matchStatus);
        Match matchUpdated = (Match) UtilsTest.getObjectFromJsonFile(MATCH_MODEL_JSON, Match.class);

        when(matchRepository.getOne(Mockito.any(UUID.class))).thenReturn(matchEntityFound);
        when(matchRepository.save(Mockito.any(MatchEntity.class))).thenReturn(matchEntityUpdated);
        when(matchMapper.entityToModel(Mockito.any(MatchEntity.class))).thenReturn(matchUpdated);

        Match match = matchService.updateStatusMatch(matchId, matchStatus);

        assertEquals(match.getId(), matchId);
        assertEquals(match.getStatus(), MatchStatus.valueOf(matchStatus));
    }

    @Test
    public void testStartMatchNotExisting(){
        String matchId = "95b590b0-8b2e-4552-8866-096a25f064ae";
        String matchStatus = "RUNNING";
        when(matchRepository.getOne(Mockito.any(UUID.class))).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> matchService.updateStatusMatch(matchId, matchStatus));

        assertEquals(exception.getMessage(), "The match does not exist");
    }

    @Test
    public void finishMatchOK(){
        String matchId = "95b590b0-8b2e-4552-8866-096a25f064ae";
        MatchEntity matchEntityFound = (MatchEntity) UtilsTest.getObjectFromJsonFile(MATCH_ENTITY_JSON, MatchEntity.class);
        MatchEntity matchUpdated = matchEntityFound;
        matchUpdated.setStatus("FINISHED");

        when(matchRepository.getOne(Mockito.any(UUID.class))).thenReturn(matchEntityFound);
        when(matchRepository.save(Mockito.any(MatchEntity.class))).thenReturn(matchUpdated);

        assertDoesNotThrow(() -> matchService.finishMatch(matchId));
    }

    @Test
    public void finishMatchNotExisting(){
        String matchId = "95b590b0-8b2e-4552-8866-096a25f064ae";
        when(matchRepository.getOne(Mockito.any(UUID.class))).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> matchService.finishMatch(matchId));

        assertEquals(exception.getMessage(), "The match does not exist");
    }

    @Test
    public void getAllMatchesOK() throws ParseException {
        List<MatchEntity> matchEntities = new ArrayList<>();
        MatchEntity matchEntity1 = (MatchEntity) UtilsTest.getObjectFromJsonFile(MATCH_ENTITY_JSON, MatchEntity.class);
        matchEntity1.setId(UUID.randomUUID());
        matchEntity1.setCreatedDate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-01-01 00:00:00").getTime()));

        MatchEntity matchEntity2 = (MatchEntity) UtilsTest.getObjectFromJsonFile(MATCH_ENTITY_JSON, MatchEntity.class);
        matchEntity2.setId(UUID.randomUUID());

        MatchEntity matchEntity3 = (MatchEntity) UtilsTest.getObjectFromJsonFile(MATCH_ENTITY_JSON, MatchEntity.class);
        matchEntity3.setId(UUID.randomUUID());

        MatchEntity matchEntity4 = (MatchEntity) UtilsTest.getObjectFromJsonFile(MATCH_ENTITY_JSON, MatchEntity.class);
        matchEntity4.setId(UUID.randomUUID());

        matchEntities.add(matchEntity1);
        matchEntities.add(matchEntity2);
        matchEntities.add(matchEntity3);
        matchEntities.add(matchEntity4);

        when(matchRepository.findAll(Mockito.any(Sort.class))).thenReturn(matchEntities);

        List<Match> matches = matchService.getAllMatches();

        assertEquals(matches.size(), 4);
    }
}
