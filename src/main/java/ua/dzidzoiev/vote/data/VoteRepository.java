package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.model.Vote;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by midnight coder on 25-May-15.
 */
@RequestScoped
public class VoteRepository {
    @Inject
    EntityManager em;

    public Vote findById(Long id) {
        return em.find(Vote.class, id);
    }

    public void create(Vote c) {
        em.persist(c);
    }

    public void update(Vote c) {
        em.merge(c);
    }

    public void remove(Vote c) {
        em.remove(c);
    }
}
