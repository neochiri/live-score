package com.ryc.score.service.impl;

import com.ryc.score.mapper.ScoreMapper;
import com.ryc.score.model.Score;
import com.ryc.score.repository.MatchRepository;
import com.ryc.score.repository.ScoreRepository;
import com.ryc.score.service.iface.ScoreService;

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
        return null;
    }
}
