package com.ryc.score.service.impl;

import com.ryc.score.entity.MatchEntity;
import com.ryc.score.mapper.MatchMapper;
import com.ryc.score.model.Match;
import com.ryc.score.model.MatchStatus;
import com.ryc.score.repository.MatchRepository;
import com.ryc.score.service.iface.MatchService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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
        checkExistingMatch(matchEntityFound);
        if(!isInEnum(matchStatus, MatchStatus.class)){
            throw new RuntimeException("The status is not correct");
        }
        matchEntityFound.setStatus(MatchStatus.valueOf(matchStatus).getValue());
        MatchEntity matchEntityUpdated = matchRepository.save(matchEntityFound);
        Match matchUpdated = matchMapper.entityToModel(matchEntityUpdated);
        return matchUpdated;
    }

    @Override
    public List<Match> getAllMatches() {
        List<MatchEntity> matchEntities = matchRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        List<Match> matches = matchEntities.stream().map(matchEntity -> matchMapper.entityToModel(matchEntity)).collect(Collectors.toList());
        return matches;
    }

    private void checkExistingMatch(MatchEntity matchEntityFound){
        if(Objects.isNull(matchEntityFound)){
            throw new RuntimeException("The match does not exist");
        }
    }

    public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }
}
