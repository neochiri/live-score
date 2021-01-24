package com.ryc.score.service.iface;

import com.ryc.score.model.Match;

public interface MatchService {
    Match updateStatusMatch(String matchId, String statusMatch);
}
