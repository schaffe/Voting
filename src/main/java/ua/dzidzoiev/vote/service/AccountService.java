package ua.dzidzoiev.vote.service;

import org.picketlink.authorization.annotations.RolesAllowed;
import org.picketlink.idm.model.basic.User;
import ua.dzidzoiev.vote.data.VoterRepository;
import ua.dzidzoiev.vote.model.Voter;
import ua.dzidzoiev.vote.model.dto.AccountRegistration;
import ua.dzidzoiev.vote.security.ApplicationRoles;
import ua.dzidzoiev.vote.security.IdentityModelManager;
import ua.dzidzoiev.vote.service.annotation.CurrentVoter;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static ua.dzidzoiev.vote.security.ApplicationRoles.*;

@Stateless
public class AccountService {

    @Inject
    private IdentityModelManager identityModelManager;

    @Inject
    private VoterRepository voterRepository;

    @Resource
    private SessionContext context;

    @Produces
    @CurrentVoter
    public Voter getCurrentVoter() {
        User user = identityModelManager.getLoggedInUser();
        return voterRepository.findByLoginName(user.getLoginName());
    }

    public Voter registerVoter(AccountRegistration registrationData) {
        Voter newVoter = new Voter();

        newVoter.setLoginName(registrationData.getLoginName());
        newVoter.setFirstName(registrationData.getFirstName());
        newVoter.setSurname(registrationData.getSurname());
        newVoter.setPersonalId(registrationData.getPersonalId());

        voterRepository.create(newVoter);

        try {
            User user = identityModelManager.createAccount(registrationData);
            identityModelManager.grantRole(user, ApplicationRoles.VOTER);
        } catch (Exception e) {
            context.setRollbackOnly();
        }

        return newVoter;
    }

    @RolesAllowed(ADMIN)
    public void removeVoter(Voter voter) {
        User u = getUser(voter);
        identityModelManager.removeUser(u);

        voterRepository.remove(voter);
    }

    @RolesAllowed(ADMIN)
    public void grantAdminRole(Voter voter) {
        identityModelManager.grantRole(getUser(voter), ApplicationRoles.ADMIN);
    }

    @RolesAllowed(ADMIN)
    public void grantStatisticsViewerRole(Voter voter) {
        identityModelManager.grantRole(getUser(voter), ApplicationRoles.STATISTIC_VIEWER);
    }

    private User getUser(Voter voter) {
        return identityModelManager.findByLoginName(voter.getLoginName());
    }

}
