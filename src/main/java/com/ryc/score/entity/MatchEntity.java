package com.ryc.score.entity;


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
    private Timestamp startTime;
    private Timestamp endTime;
    @OneToOne(mappedBy = "match")
    private ScoreEntity score;
}
