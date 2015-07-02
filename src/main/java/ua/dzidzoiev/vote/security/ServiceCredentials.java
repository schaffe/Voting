package ua.dzidzoiev.vote.security;

import org.picketlink.idm.credential.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ServiceCredentials extends AbstractBaseCredentials {

    private String login;
    private Object credential;

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
        if (credential != null && credential instanceof TokenCredential) {
            TokenCredential token = (TokenCredential) credential;
            return token.getToken().getToken();
        }
        return null;
    }

    public void setToken(Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Token can not be null.");
        }

        this.credential = new TokenCredential(token);
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
        if (credential instanceof Credentials) {
            ((Credentials) credential).invalidate();
        }
        credential = null;
    }

}
