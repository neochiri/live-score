package com.ryc.score.service.impl;

import com.ryc.score.entity.MatchEntity;
import com.ryc.score.entity.ScoreEntity;
import com.ryc.score.mapper.ScoreMapper;
import com.ryc.score.model.Score;
import com.ryc.score.repository.MatchRepository;
import com.ryc.score.repository.ScoreRepository;
import com.ryc.score.service.iface.ScoreService;

import java.util.Objects;
import java.util.UUID;

public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final MatchRepository matchRepository;
    private final ScoreMapper scoreMapper;

    public ScoreServiceImpl(ScoreRepository scoreRepository, MatchRepository matchRepository, ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.matchRepository = matchRepository;
        this.scoreMapper = scoreMapper;
    }

    @Override
    public Score updateScore(String matchId, Score scoreToUpdate) {
        MatchEntity matchEntityFound = matchRepository.getOne(UUID.fromString(matchId));
        if(Objects.isNull(matchEntityFound)){
            throw new RuntimeException("The match does not exist");
        }
        ScoreEntity scoreEntityFound = scoreRepository.getOne(UUID.fromString(scoreToUpdate.getId()));
        if(Objects.isNull(scoreEntityFound)){
            throw new RuntimeException("The score does not exist");
        }
        ScoreEntity scoreEntityToUpdate = scoreMapper.modelToEntity(scoreToUpdate);
        ScoreEntity scoreEntityUpdated = scoreRepository.save(scoreEntityToUpdate);
        Score scoreUpdated = scoreMapper.entityToModel(scoreEntityUpdated);
        return scoreUpdated;
    }
}
