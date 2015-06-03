package ua.dzidzoiev.vote.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by midnight coder on 25-May-15.
 */
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;

    @OneToOne
    @JoinColumn(name = "voter_id")
    private
    Voter voter;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private
    Candidate candidate;

    @Column
    private
    Date date;

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
