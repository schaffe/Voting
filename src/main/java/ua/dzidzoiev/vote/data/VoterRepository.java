package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Voter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by midnight coder on 25-May-15.
 */
@RequestScoped
public class VoterRepository {
    @Inject
    EntityManager em;

    public Voter findById(Long id) {
        return em.find(Voter.class, id);
    }

    public Voter findByLoginName(String loginName) {
        Query query = em.createQuery("select Voter from Voter v where v.loginName = :loginName", Voter.class);
        query.setParameter("loginName", loginName);
        return (Voter) query.getSingleResult();
    }

    public Voter findByPersonalId(String personalId) {
        Query query = em.createQuery("select Voter from Voter v where v.personalId = :personalId", Voter.class);
        query.setParameter("personalId", personalId);
        return (Voter) query.getSingleResult();
    }

    public void create(Voter c) {
        em.persist(c);
    }

    public void update(Voter c) {
        em.merge(c);
    }

    public void remove(Voter c) {
        em.remove(c);
    }
}
