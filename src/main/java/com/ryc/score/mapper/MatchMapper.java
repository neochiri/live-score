package com.ryc.score.mapper;

import com.ryc.score.entity.MatchEntity;
import com.ryc.score.model.Match;
import org.modelmapper.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class MatchMapper {

    private final ModelMapper modelMapper;

    public MatchMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        Converter<Timestamp, LocalDateTime> toStringDate = new AbstractConverter<>() {
            @Override
            protected LocalDateTime convert(Timestamp source) {
                return source.toLocalDateTime();
            }
        };
        Provider<LocalDateTime> localDateProvider = new AbstractProvider<>() {
            @Override
            public LocalDateTime get() {
                return LocalDateTime.now();
            }
        };
        modelMapper.createTypeMap(Timestamp.class, LocalDateTime.class);
        modelMapper.addConverter(toStringDate);
        modelMapper.getTypeMap(Timestamp.class, LocalDateTime.class).setProvider(localDateProvider);
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
