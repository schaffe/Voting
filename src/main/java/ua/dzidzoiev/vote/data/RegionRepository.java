package ua.dzidzoiev.vote.data;

import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by midnight coder on 25-May-15.
 */
@Stateless
public class RegionRepository {

    @Inject
    EntityManager em;

    public Region findById(Long id) {
        return em.find(Region.class, id);
    }


    public List<Region> getAll() {
        return em
                .createQuery("Select region from Region region", Region.class)
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
