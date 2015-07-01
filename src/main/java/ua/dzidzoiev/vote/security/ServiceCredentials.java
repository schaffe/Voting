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

    //TODO create unified type "CREDENTIALS" and remove references to another field
    private String serviceKey;
    private String login;
    private Password password;
    private String token;
    private Object credential;

    public ServiceCredentials() {
    }

    public ServiceCredentials(String login, String password, String serviceKey) {
        this.login = login;
        this.password = new Password(password);
        this.serviceKey = serviceKey;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        if (credential != null && credential instanceof Password) {
            Password ptp = (Password) credential;
            return new String(ptp.getValue());
        }
        return null;
    }

    /**
     * Convenience method that allows a plain text password credential to be set
     */
    public void setPassword(final String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can not be null.");
        }

        this.credential = new Password(password.toCharArray());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Object getCredential() {
        return credential;
    }

    public void setCredential(Object credential) {
        this.credential = credential;
    }

    @Override
    public void invalidate() {
        this.login = null;
        this.password = null;
        this.serviceKey = null;
        this.token = null;
    }

}
