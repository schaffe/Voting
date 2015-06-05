package ua.dzidzoiev.vote.util;

import ua.dzidzoiev.vote.model.Voter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

public class Resources {

    @Produces
    @PersistenceContext(unitName = "Voting")
    private EntityManager em;

//    @Produces
//    @Named("security-em")
//    @PersistenceContext(unitName = "Security")
//    private EntityManager securityEm;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}