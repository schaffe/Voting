package ua.dzidzoiev.vote.service;

import org.picketlink.authorization.annotations.RolesAllowed;
import org.picketlink.idm.model.basic.User;
import ua.dzidzoiev.vote.data.RegionRepository;
import ua.dzidzoiev.vote.data.VoterRepository;
import ua.dzidzoiev.vote.model.Voter;
import ua.dzidzoiev.vote.model.dto.AccountRegistration;
import ua.dzidzoiev.vote.model.dto.auth.AuthLoginElement;
import ua.dzidzoiev.vote.security.ApplicationRoles;
import ua.dzidzoiev.vote.security.IdentityModelManager;
import ua.dzidzoiev.vote.service.annotation.inject.CurrentVoter;
import ua.dzidzoiev.vote.service.annotation.validation.Registered;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static ua.dzidzoiev.vote.security.ApplicationRoles.*;

@Stateless
public class AccountService {

    @Inject
    private IdentityModelManager identityModelManager;

    @Inject
    private VoterRepository voterRepository;

    @Inject
    private RegionRepository regionRepository;

    @Inject
    @Registered
    private Event<User> registrationEvent;

//    @Resource
//    private SessionContext context;

    @Produces
    @CurrentVoter
    public Voter getCurrentVoter() {
        User user = identityModelManager.getLoggedInUser();
        return voterRepository.findByLoginName(user.getLoginName());
    }

    public AuthLoginElement registerVoter(AccountRegistration registrationData) {
        Voter newVoter = new Voter();

        String loginName = registrationData.getLoginName();
        if(loginName == null || loginName.isEmpty()) {
            loginName = registrationData.getPersonalId();
        }

        newVoter.setLoginName(loginName);
        newVoter.setFirstName(registrationData.getFirstName());
        newVoter.setSurname(registrationData.getSurname());
        newVoter.setPersonalId(registrationData.getPersonalId());
        newVoter.setRegion(regionRepository.findByCode(registrationData.getRegionCode()));

        AuthLoginElement userData = identityModelManager.createAccount(
                registrationData.getFirstName(),
                registrationData.getSurname(),
                loginName,
                registrationData.getPassword());
        User user = identityModelManager.findByLoginName(userData.getUsername());
        identityModelManager.grantRole(user, ApplicationRoles.VOTER);
//        registrationEvent.fire(user);

        voterRepository.create(newVoter);

        return userData;
    }

    @RolesAllowed(ADMIN)
    public void removeVoter(Voter voter) {
        User u = getUser(voter);
        identityModelManager.removeUser(u);

        voterRepository.remove(voter);
    }

    @RolesAllowed(ADMIN)
    public void removeVoter(String username) {
        identityModelManager.removeUser(username);
        Voter voter = voterRepository.findByLoginName(username);
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
