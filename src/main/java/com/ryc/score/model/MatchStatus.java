package com.ryc.score.model;

public enum MatchStatus {

    NOT_STARTED("NOT_STARTED"),
    RUNNING("RUNNING"),
    FINISHED("FINISHED");

    MatchStatus(String value){
        this.value = value;
    }

    private String value;

    public String getValue(){
        return this.value;
    }
}
