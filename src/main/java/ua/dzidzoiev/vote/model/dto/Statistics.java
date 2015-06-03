package ua.dzidzoiev.vote.model.dto;

import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;

import java.util.List;
import java.util.Map;

/**
 * Created by midnight coder on 27-May-15.
 */
public class Statistics {

    private Candidate candidate;
    private Integer votes;

    public Statistics() {
    }

    public Statistics(Candidate candidate, Integer votes) {
        this.candidate = candidate;
        this.votes = votes;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
