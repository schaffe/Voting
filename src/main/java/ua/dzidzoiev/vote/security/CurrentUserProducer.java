package ua.dzidzoiev.vote.security;

import org.picketlink.idm.model.basic.User;
import ua.dzidzoiev.vote.security.annotations.CurrentUserParameterBinding;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by midnight coder on 27-Jul-15.
 */
public class CurrentUserProducer {
    @Inject
    private IdentityModelManager identityModelManager;

    @Produces
    @CurrentUserParameterBinding
    public User getCurrentUser() {
        return identityModelManager.getLoggedInUser();
    }
}
