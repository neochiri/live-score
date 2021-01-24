package com.ryc.score.service.iface;

import com.ryc.score.model.Score;

public interface ScoreService {
    Score updateScore(String matchId, Score scoreToUpdate);
}
