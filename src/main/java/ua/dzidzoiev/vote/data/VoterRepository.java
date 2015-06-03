package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.model.Vote;
import ua.dzidzoiev.vote.model.Voter;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by midnight coder on 25-May-15.
 */
public class VoterRepository {
    @Inject
    EntityManager em;

    public Voter findById(Long id) {
        return em.find(Voter.class, id);
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
