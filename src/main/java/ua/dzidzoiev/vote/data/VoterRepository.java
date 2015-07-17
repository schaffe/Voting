package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Voter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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
        return em
                .createQuery("select v from Voter v where v.loginName = :loginName", Voter.class)
                .setParameter("loginName", loginName)
                .getSingleResult();
    }

    public Voter findByPersonalId(String personalId) {
        return em
                .createQuery("select v from Voter v where v.personalId = :personalId", Voter.class)
                .setParameter("personalId", personalId)
                .getSingleResult();
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
