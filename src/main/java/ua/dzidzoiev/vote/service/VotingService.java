package ua.dzidzoiev.vote.service;

import org.picketlink.authorization.annotations.RolesAllowed;
import ua.dzidzoiev.vote.data.CandidateRepository;
import ua.dzidzoiev.vote.data.VoteRepository;
import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.model.Vote;
import ua.dzidzoiev.vote.model.Voter;
import ua.dzidzoiev.vote.service.annotation.inject.CurrentVoter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;

import static ua.dzidzoiev.vote.security.ApplicationRoles.VOTER;

@Stateless
@RolesAllowed(VOTER)
public class VotingService {

    @Inject
    EntityManager em;

    @Inject
    CandidateRepository candidateRepository;

    @Inject
    VoteRepository voteRepository;

    @Inject
    @CurrentVoter
    Voter voter;

    public void vote(String candId) {
        Candidate candidate = candidateRepository.findByRegId(candId);
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
