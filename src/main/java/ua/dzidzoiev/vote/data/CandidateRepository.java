package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by midnight coder on 25-May-15.
 */
@RequestScoped
public class CandidateRepository {
    @Inject
    EntityManager em;

    public Candidate findById(Long id) {
        return em.find(Candidate.class, id);
    }

    public Candidate findByRegId(String regId) {
        return em
                .createQuery("SELECT c FROM Candidate c WHERE c.registrationId = :regId", Candidate.class)
                .setParameter("regId", regId)
                .getSingleResult();
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
                .createQuery("SELECT c FROM Candidate c WHERE c.region.id = :regionId ORDER BY c.surname", Candidate.class)
                .setParameter("regionId", regionId)
                .getResultList();
    }

    public List<Candidate> getAllCandidatesInRegion(String regionCode) {
        return em
                .createQuery("SELECT c FROM Candidate c WHERE c.region.code = :regionCode ORDER BY c.surname", Candidate.class)
                .setParameter("regionCode", regionCode)
                .getResultList();
    }
}
