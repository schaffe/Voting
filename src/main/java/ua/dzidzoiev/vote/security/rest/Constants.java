package ua.dzidzoiev.vote.security.rest;

/**
 * Created by midnight coder on 03-Jul-15.
 */
public interface Constants {
    interface Headers {
        String AUTH_TOKEN = "X-Auth-Token";
    }

    interface ContentTypes {
        String USERNAME_PASSWORD_CREDENTIAL_CONTENT_TYPE = "application/x-authc-username-password+json";
        String TOKEN_CONTENT_CREDENTIAL_TYPE = "application/x-authc-token";
    }
}
