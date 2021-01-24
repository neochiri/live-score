package com.ryc.score;

import com.ryc.score.model.Match;
import com.ryc.score.service.iface.MatchService;
import com.ryc.score.service.iface.ScoreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
