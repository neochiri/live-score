package com.ryc.score.mapper;

import com.ryc.score.entity.MatchEntity;
import com.ryc.score.model.Match;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    private final ModelMapper modelMapper;

    public MatchMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Match entityToModel(MatchEntity matchEntity){
        Match match = modelMapper.map(matchEntity, Match.class);
        return match;
    }

    public MatchEntity modelToEntity(Match match){
        MatchEntity matchEntity = modelMapper.map(match, MatchEntity.class);
        return matchEntity;
    }
}
