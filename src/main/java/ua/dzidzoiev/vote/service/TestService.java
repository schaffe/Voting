package ua.dzidzoiev.vote.service;

import org.picketlink.authorization.annotations.LoggedIn;

import javax.ejb.Stateless;

/**
 * Created by midnight coder on 02-Jul-15.
 */
@Stateless
public class TestService {

    @LoggedIn
    public String testGet() {
        return "demo get method";
    }

    @LoggedIn
    public String testPost() {
        return "demo post method";
    }

    public String unsecured() {
        return "unsecured method";
    }
}
