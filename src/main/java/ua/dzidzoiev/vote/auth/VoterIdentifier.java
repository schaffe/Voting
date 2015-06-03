package ua.dzidzoiev.vote.auth;

import ua.dzidzoiev.vote.data.VoterRepository;
import ua.dzidzoiev.vote.model.Voter;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

/**
 * Created by midnight coder on 26-May-15.
 */
@Stateless
public class VoterIdentifier {
    @Inject
    VoterRepository repository;

    @Produces
    @SessionScoped
    @Named("logged_voter")
    public Voter getVoter() {
        return repository.findById(1l);
    }
}
