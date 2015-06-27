package ua.dzidzoiev.vote.util;

import org.picketlink.annotations.PicketLink;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

public class Resources {

    @Produces
    @PersistenceContext(unitName = "Voting")
    private EntityManager em_data;

    @PersistenceContext(unitName = "picketlink-default")
    @Named("security")
    private EntityManager em_security;

//    @Produces
//    @Named("security-em")
//    @PersistenceContext(unitName = "Security")
//    private EntityManager securityEm;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    /*
     * Since we are using JPAIdentityStore to store identity-related data, we must provide it with an EntityManager via a
     * producer method or field annotated with the @PicketLink qualifier.
     */
    @Produces
    @PicketLink
    public EntityManager getPicketLinkEntityManager() {
        return em_security;
    }
}