package ua.dzidzoiev.vote.service;

import org.picketlink.idm.model.basic.User;
import ua.dzidzoiev.vote.data.VoterRepository;
import ua.dzidzoiev.vote.model.Voter;
import ua.dzidzoiev.vote.model.dto.AccountRegistration;
import ua.dzidzoiev.vote.security.ApplicationRoles;
import ua.dzidzoiev.vote.security.IdentityModelManager;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccountService {

    @Inject
    private IdentityModelManager identityModelManager;

    @Inject
    private VoterRepository voterRepository;

    @Resource
    private SessionContext context;

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

    public void removeVoter(Voter voter) {
        User u = getUser(voter);
        identityModelManager.removeUser(u);

        voterRepository.remove(voter);
    }

    public void grantAdminRole(Voter voter) {
        identityModelManager.grantRole(getUser(voter), ApplicationRoles.ADMIN);
    }

    public void grantStatisticsViewerRole(Voter voter) {
        identityModelManager.grantRole(getUser(voter), ApplicationRoles.STATISTIC_VIEWER);
    }

    private User getUser(Voter voter) {
        return identityModelManager.findByLoginName(voter.getLoginName());
    }

}
