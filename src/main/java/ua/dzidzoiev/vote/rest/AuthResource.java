package ua.dzidzoiev.vote.rest;

import org.picketlink.http.AccessDeniedException;
import ua.dzidzoiev.vote.security.AuthenticationService;
import ua.dzidzoiev.vote.security.DemoAuthenticator;
import ua.dzidzoiev.vote.service.TestService;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.security.GeneralSecurityException;

//@Stateless
public class AuthResource implements AuthResourceProxy {
    @Inject
    DemoAuthenticator demoAuthenticator;

    @Inject
    AuthenticationService authService;

    @Inject
    TestService testService;

    private static final long serialVersionUID = -6663599014192066936L;

    @Override
    public Response login(
            @Context HttpHeaders httpHeaders,
            @FormParam("username") String username,
            @FormParam("password") String password) {

        try {
            String authToken = authService.authenticateWithPassword(username, password);
            return getNoCacheResponseBuilder(Response.Status.OK).entity(authToken).build();

        } catch (final LoginException ex) {
            return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).entity("Problem matching username and password").build();
        }
    }

    @Override
    public Response demoGetMethod() {
        try {
            return getNoCacheResponseBuilder(Response.Status.OK).entity(testService.testGet()).build();
        } catch (AccessDeniedException e) {
            return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Override
    public Response usecured() {
        return getNoCacheResponseBuilder(Response.Status.OK).entity(testService.unsecured()).build();
    }

    @Override
    public Response demoPostMethod() {
        try {
            return getNoCacheResponseBuilder(Response.Status.OK).entity(testService.testPost()).build();
        } catch (AccessDeniedException e) {
            return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Override
//    @LoggedIn
    public Response logout(
            @Context HttpHeaders httpHeaders) {
        try {
            authService.logout();
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