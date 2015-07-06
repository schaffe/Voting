package ua.dzidzoiev.vote.rest;

import org.jboss.resteasy.annotations.cache.NoCache;
import ua.dzidzoiev.vote.security.AuthenticationService;
import ua.dzidzoiev.vote.security.rest.AuthToken;
import ua.dzidzoiev.vote.util.MessageBuilder;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.security.GeneralSecurityException;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthenticationService authService;

    @POST
    @Path("login")
    @NoCache
    public Response login(
//            @Context HttpHeaders httpHeaders,
            @FormParam("username") String username,
            @FormParam("password") String password) {

        try {
            String authToken = authService.authenticateWithPassword(username, password);
            return MessageBuilder.ok().token(authToken).build();
        } catch (final LoginException ex) {
            return MessageBuilder.authenticationRequired().message("Problem matching username and password").build();
        }
    }

    @POST
    @Path("logout")
    @AuthToken
    @NoCache
    public Response logout(
            @Context HttpHeaders httpHeaders) {
        try {
            authService.logout();
            return MessageBuilder.ok().build();
        } catch (GeneralSecurityException e) {
            return MessageBuilder.internalServerError().message(e.getMessage()).build();
        }
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
        CacheControl cc = new CacheControl();
        cc.setNoCache( true );
        cc.setMaxAge( -1 );
        cc.setMustRevalidate( true );

        return Response.status( status ).cacheControl( cc );
    }

}