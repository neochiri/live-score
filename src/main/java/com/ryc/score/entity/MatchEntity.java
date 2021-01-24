package com.ryc.score.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "match")
@Data
public class MatchEntity {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String homeTeam;
    @Column(nullable = false)
    private String awayTeam;
    private String status;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;
    @OneToOne(mappedBy = "match")
    private ScoreEntity score;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdDate;
}
