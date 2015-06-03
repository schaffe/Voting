package ua.dzidzoiev.vote.service;

import ua.dzidzoiev.vote.data.CandidateRepository;
import ua.dzidzoiev.vote.data.VoteRepository;
import ua.dzidzoiev.vote.data.VoterRepository;
import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.model.Vote;
import ua.dzidzoiev.vote.model.Voter;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by midnight coder on 26-May-15.
 */
@Stateless
public class VotingService {

    @Inject
    EntityManager em;

    @Inject
    CandidateRepository candidateRepository;

    @Inject
    VoteRepository voteRepository;

    @Inject
    @Named("logged_voter")
    Voter voter;

    public void vote(long candId) {
        Candidate candidate = candidateRepository.findById(candId);
        Region region = candidate.getRegion();
        voter = em.find(Voter.class, 1l);
        Region voterRegion = voter.getRegion();
//        if (voterRegion.equals(region)) {
//            Boolean existVote = em
//                    .createQuery("select case when (count(v) > 0)  then true else false end from Vote v where v.voter = :voter", Boolean.class)
//                    .setParameter("voter", voter)
//                    .getSingleResult();
//            if (!existVote) {
                Vote v = new Vote();
                v.setCandidate(candidate);
                v.setDate(new Date());
                v.setVoter(voter);
                voteRepository.create(v);
//            }
//        }
    }
}
