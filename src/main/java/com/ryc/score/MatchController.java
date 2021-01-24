package com.ryc.score;

import com.ryc.score.model.Match;
import com.ryc.score.model.Score;
import com.ryc.score.service.iface.MatchService;
import com.ryc.score.service.iface.ScoreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v01/match")
public class MatchController {

    private final MatchService matchService;
    private final ScoreService scoreService;

    public MatchController(MatchService matchService, ScoreService scoreService) {
        this.matchService = matchService;
        this.scoreService = scoreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Match>> getAllMatches(){
        List<Match> matches = matchService.getAllMatches();
        return new ResponseEntity<>(matches, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{matchId}/{matchStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Match> updateStatusMatch(@PathVariable String matchId, @PathVariable String matchStatus){
        Match match = matchService.updateStatusMatch(matchId, matchStatus);
        return new ResponseEntity<>(match, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{matchId}/score")
    public ResponseEntity<Score> updateScore(@PathVariable String matchId, @RequestBody Score score){
        Score scoreUpdated = scoreService.updateScore(matchId, score);
        return new ResponseEntity<>(scoreUpdated, new HttpHeaders(), HttpStatus.OK);
    }
}
