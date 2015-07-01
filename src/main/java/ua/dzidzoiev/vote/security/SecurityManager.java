package ua.dzidzoiev.vote.security;

import javax.security.auth.login.LoginException;
import java.security.GeneralSecurityException;

/**
 * Created by midnight coder on 01-Jul-15.
 */
public interface SecurityManager {
    String login(String serviceKey, String username, String password) throws LoginException;

    /**
     * The method that pre-validates if the client which invokes the REST API is
     * from a authorized and authenticated source.
     *
     * @param serviceKey The service key
     * @param authToken  The authorization token generated after login
     * @return TRUE for acceptance and FALSE for denied.
     */
    boolean isAuthTokenValid(String serviceKey, String authToken);

    /**
     * This method checks is the service key is valid
     *
     * @param serviceKey
     * @return TRUE if service key matches the pre-generated ones in service key
     * storage. FALSE for otherwise.
     */
    boolean isServiceKeyValid(String serviceKey);

    void logout(String serviceKey, String authToken) throws GeneralSecurityException;
}
