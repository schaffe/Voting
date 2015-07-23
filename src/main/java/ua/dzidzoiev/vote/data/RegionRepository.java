package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by midnight coder on 25-May-15.
 */
@RequestScoped
public class RegionRepository {

    @Inject
    EntityManager em;

    public Region findById(Long id) {
        return em.find(Region.class, id);
    }

    public Region findByCode(String code) {
        return em
                .createQuery("SELECT r FROM Region r WHERE r.code = :code", Region.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    public List<Region> getAll() {
        return em
                .createQuery("SELECT r FROM Region r ORDER BY r.name", Region.class)
                .getResultList();
    }

    public void create(Region c) {
        em.persist(c);
    }

    public void update(Region c) {
        em.merge(c);
    }

    public void remove(Region c) {
        em.remove(c);
    }
}
