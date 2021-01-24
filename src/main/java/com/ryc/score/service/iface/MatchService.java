package com.ryc.score.service.iface;

import com.ryc.score.model.Match;

import java.util.List;

public interface MatchService {
    Match updateStatusMatch(String matchId, String statusMatch);
    void finishMatch(String matchId);
    List<Match> getAllMatches();
}
