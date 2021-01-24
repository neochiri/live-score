package com.ryc.score.mapper;

import com.ryc.score.entity.ScoreEntity;
import com.ryc.score.model.Score;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ScoreMapper {

    private final ModelMapper modelMapper;

    public ScoreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Score entityToModel(ScoreEntity scoreEntity){
        Score score = modelMapper.map(scoreEntity, Score.class);
        return score;
    }

    public ScoreEntity modelToEntity(Score score){
        ScoreEntity scoreEntity = modelMapper.map(score, ScoreEntity.class);
        return scoreEntity;
    }
}
