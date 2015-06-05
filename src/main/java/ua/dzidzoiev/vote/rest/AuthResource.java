package ua.dzidzoiev.vote.rest;

import javax.ejb.Stateless;
import javax.security.auth.login.LoginException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.security.GeneralSecurityException;

@Stateless
public class AuthResource implements AuthResourceProxy {

    private static final long serialVersionUID = -6663599014192066936L;

    @Override
    public Response login(
            @Context HttpHeaders httpHeaders,
            @FormParam("username") String username,
            @FormParam("password") String password) {

        DemoAuthenticator demoAuthenticator = DemoAuthenticator.getInstance();
        String serviceKey = httpHeaders.getHeaderString(SERVICE_KEY);

        try {
            String authToken = demoAuthenticator.login(serviceKey, username, password);
            return getNoCacheResponseBuilder(Response.Status.OK).entity(authToken).build();

        } catch (final LoginException ex) {
            return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).entity("Problem matching service key, username and password").build();
        }
    }

    @Override
    public Response demoGetMethod() {
        return getNoCacheResponseBuilder(Response.Status.OK).entity("Executed demoGetMethod").build();
    }

    @Override
    public Response demoPostMethod() {
        return getNoCacheResponseBuilder(Response.Status.ACCEPTED).entity("Executed demoPostMethod").build();
    }

    @Override
    public Response logout(
            @Context HttpHeaders httpHeaders) {
        try {
            DemoAuthenticator demoAuthenticator = DemoAuthenticator.getInstance();
            String serviceKey = httpHeaders.getHeaderString(SERVICE_KEY);
            String authToken = httpHeaders.getHeaderString(AUTH_TOKEN);

            demoAuthenticator.logout(serviceKey, authToken);

            return getNoCacheResponseBuilder(Response.Status.NO_CONTENT).build();
        } catch (final GeneralSecurityException ex) {
            return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setMaxAge(-1);
        cc.setMustRevalidate(true);

        return Response.status(status).cacheControl(cc);
    }


}