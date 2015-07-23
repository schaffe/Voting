package ua.dzidzoiev.vote.service;

import org.picketlink.authorization.annotations.RolesAllowed;
import ua.dzidzoiev.vote.data.CandidateRepository;
import ua.dzidzoiev.vote.data.RegionRepository;
import ua.dzidzoiev.vote.data.VoteRepository;
import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.model.dto.Statistics;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static ua.dzidzoiev.vote.security.ApplicationRoles.*;

/**
 * Created by midnight coder on 27-May-15.
 */
@Stateless
public class StatisticsService {

    @Inject
    EntityManager em;

    @Inject
    RegionRepository regionRepository;

    @Inject
    CandidateRepository candidateRepository;

    @Inject
    VoteRepository voteRepository;

    @RolesAllowed(STATISTIC_VIEWER)
    public  List<Statistics> getHotStats(String regionCode) {
        Region region = regionRepository.findByCode(regionCode);
        List<Candidate> candidates = region.getCandidates();
        List<Statistics> statistics = new ArrayList<>();

        TypedQuery<Long> q = em.createQuery("select COUNT(v) from Vote v where v.candidate = :candidate", Long.class);
        for (Candidate c : candidates) {
            Long votes = q
                    .setParameter("candidate", c)
                    .getSingleResult();
            Integer i = (int) (long) votes;
            statistics.add(new Statistics(c, i));
        }
        return statistics;
    }

    //TODO get whole stats
    //TODO recount stats
}
