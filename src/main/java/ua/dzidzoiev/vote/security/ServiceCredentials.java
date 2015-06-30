package ua.dzidzoiev.vote.security;

import org.picketlink.idm.credential.AbstractBaseCredentials;
import org.picketlink.idm.credential.Password;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by midnight coder on 30-Jun-15.
 */
@Named
@RequestScoped
public class ServiceCredentials extends AbstractBaseCredentials {

    private Object login;
    private String userId;
    private String serviceKey;

    public ServiceCredentials() {
    }

    public ServiceCredentials(Object login, String userId, String serviceKey) {
        this.login = login;
        this.userId = userId;
        this.serviceKey = serviceKey;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User id can not be null.");
        }

        this.userId = userId;
    }

    public Object getLogin() {
        return login;
    }

    public void setLogin(Object login) {
        this.login = login;
    }

    public String getPassword() {
        if (login != null && login instanceof Password) {
            Password ptp = (Password) login;
            return new String(ptp.getValue());
        }
        return null;
    }

    /**
     * Convenience method that allows a plain text password login to be set
     */
    public void setPassword(final String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can not be null.");
        }

        this.login = new Password(password.toCharArray());
    }

    @Override
    public void invalidate() {
        this.login = null;
        this.userId = null;
        this.serviceKey = null;
    }

}
