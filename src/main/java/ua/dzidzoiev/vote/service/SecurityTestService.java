package ua.dzidzoiev.vote.service;

import org.picketlink.authorization.annotations.LoggedIn;
import ua.dzidzoiev.vote.security.annotations.*;

import javax.ejb.Stateless;

/**
 * Created by midnight coder on 28-Jul-15.
 */
@Stateless
public class SecurityTestService {

    public String publicMethod() {
        return "public method";
    }

    @LoggedIn
    public String loggedInMethod() {
        return "loggedIN method";
    }

    @StatisticsViewer
    public String statsMethod() {
        return "stats method";
    }

    @Admin
    public String adminMethod() {
        return "admin method";
    }
}
