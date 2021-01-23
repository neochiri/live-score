package com.ryc.score.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "match_score")
@Data
public class ScoreEntity {

    @Id
    private UUID id;
    @Column(nullable = false)
    private int scoreHomeTeam;
    @Column(nullable = false)
    private int scoreAwayTeam;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchEntity match;
}
