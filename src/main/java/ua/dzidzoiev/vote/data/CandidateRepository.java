package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by midnight coder on 25-May-15.
 */
@Stateless
public class CandidateRepository {
    @Inject
    EntityManager em;

    public Candidate findById(Long id) {
        return em.find(Candidate.class, id);
    }

    public void create(Candidate c) {
        em.persist(c);
    }

    public void update(Candidate c) {
        em.merge(c);
    }

    public void remove(Candidate c) {
        em.remove(c);
    }

    public List<Candidate> getAllCandidatesInRegion(long regionId) {
        return em
                .createQuery("Select candidate from Candidate candidate where candidate.region.id = :regionId", Candidate.class)
                .setParameter("regionId", regionId)
                .getResultList();
    }
}
