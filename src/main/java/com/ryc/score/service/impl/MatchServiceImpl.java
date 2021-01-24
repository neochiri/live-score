package com.ryc.score.service.impl;

import com.ryc.score.entity.MatchEntity;
import com.ryc.score.mapper.MatchMapper;
import com.ryc.score.model.Match;
import com.ryc.score.model.MatchStatus;
import com.ryc.score.repository.MatchRepository;
import com.ryc.score.service.iface.MatchService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    @Override
    public Match updateStatusMatch(String matchId, String matchStatus) {
        MatchEntity matchEntityFound = matchRepository.getOne(UUID.fromString(matchId));
        if(Objects.isNull(matchEntityFound)){
            throw new RuntimeException("The match does not exist");
        }
        matchEntityFound.setStatus(MatchStatus.valueOf(matchStatus).getValue());
        MatchEntity matchEntityUpdated = matchRepository.save(matchEntityFound);
        Match matchUpdated = matchMapper.entityToModel(matchEntityUpdated);
        return matchUpdated;
    }

    @Override
    public void finishMatch(String matchId) {
        MatchEntity matchEntityFound = matchRepository.getOne(UUID.fromString(matchId));
        matchEntityFound.setStatus("FINISHED");
        matchRepository.save(matchEntityFound);
    }
}
