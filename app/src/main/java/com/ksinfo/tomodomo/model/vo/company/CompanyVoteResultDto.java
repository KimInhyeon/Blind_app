package com.ksinfo.tomodomo.model.vo.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyVoteResultDto {
    private final int voteCountOfGood;
    private final int voteCountOfBad;

    public CompanyVoteResultDto(@JsonProperty("voteCountOfGood") int voteCountOfGood,@JsonProperty("voteCountOfBad") int voteCountOfBad) {
        this.voteCountOfGood = voteCountOfGood;
        this.voteCountOfBad = voteCountOfBad;
    }

    public int getVoteCountOfGood() {
        return voteCountOfGood;
    }

    public int getVoteCountOfBad() {
        return voteCountOfBad;
    }

}
