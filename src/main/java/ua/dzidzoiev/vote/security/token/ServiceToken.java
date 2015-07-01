package ua.dzidzoiev.vote.security.token;

import org.picketlink.idm.credential.Token;

/**
 * Created by midnight coder on 01-Jul-15.
 */
public class ServiceToken implements Token {
    private String subject;
    private String token;

    public ServiceToken(String token, String subject) {
//        super(token);
        this.token = token;
        this.subject = subject;
    }

    @Override
    public String getType() {
        return ServiceToken.class.getName();
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getToken() {
        return token;
    }
}
