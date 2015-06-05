package ua.dzidzoiev.vote.service;

import ua.dzidzoiev.vote.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class UserService {

    @Inject
    EntityManager em;


    public User findByUsernameAndPassword(String username, String password) {
        User u = em.createQuery("select u from User u where u.login = :login and u.password = :password", User.class)
                .setParameter("login", username)
                .setParameter("password", password)
                .getSingleResult();
        return u;
    }

    public void save(User user) {
        em.merge(user);
    }
}
