package ua.dzidzoiev.vote.security;

import org.apache.deltaspike.security.api.authorization.Secures;
import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.User;
import ua.dzidzoiev.vote.security.annotations.*;

import javax.enterprise.context.ApplicationScoped;

import static org.picketlink.idm.model.basic.BasicModel.getRole;
import static org.picketlink.idm.model.basic.BasicModel.hasRole;
import static ua.dzidzoiev.vote.security.ApplicationRoles.*;

/**
 * Defines the authorization logic for the @Employee and @Admin security binding types
 *
 * @author Shane Bryzak
 *
 */
@ApplicationScoped
public class SecurityAuthorizer {
    /**
     * This method is used to check if classes and methods annotated with {@link Admin} can perform
     * the operation or not
     *
     * @param identity The Identity bean, representing the currently authenticated user
     * @param identityManager The IdentityManager provides methods for checking a user's roles
     * @return true if the user can execute the method or class
     * @throws Exception
     */
    @Secures
    @Admin
    public boolean doAdminCheck(Identity identity, IdentityManager identityManager, RelationshipManager relationshipManager) throws Exception {
        return isAdmin(identity, identityManager, relationshipManager);
    }

    @Secures
    @Voter
    public boolean doVoterCheck(Identity identity, IdentityManager identityManager, RelationshipManager relationshipManager) throws Exception {
        return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, VOTER)) ||
                isAdmin(identity, identityManager, relationshipManager);
    }

    @Secures
    @StatisticsViewer
    public boolean doStatistscsViewerCheck(Identity identity, IdentityManager identityManager, RelationshipManager relationshipManager) throws Exception {
        return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, STATISTIC_VIEWER)) ||
                isAdmin(identity, identityManager, relationshipManager);
    }

    @Secures
    @CurrentUser
    public boolean doCurrentUserCheck(Identity identity,
                                      IdentityManager identityManager,
                                      RelationshipManager relationshipManager,
                                      @CurrentUserParameterBinding User currentUser) throws Exception {
        return identity.getAccount().equals(currentUser) ||
                isAdmin(identity, identityManager, relationshipManager);
    }

    private boolean isAdmin(Identity identity, IdentityManager identityManager, RelationshipManager relationshipManager) {
        return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMIN));
    }
}
