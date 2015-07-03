package ua.dzidzoiev.vote.security.rest;

import ua.dzidzoiev.vote.security.AuthenticationService;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

import static ua.dzidzoiev.vote.security.rest.Constants.Headers.AUTH_TOKEN;

@AuthToken
@Provider
public class TokenFilter implements ContainerRequestFilter {
    @Inject
    AuthenticationService authenticationService;

    @Inject
    private Logger log;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.info(String.format("Token filtering %s %s", requestContext.getMethod(), requestContext.getUriInfo().getPath()));

        String authToken = requestContext.getHeaderString(AUTH_TOKEN);
        log.fine(String.format("Received token %s", authToken));

        try {
            authenticationService.authenticateWithToken(authToken);
        } catch (LoginException e) {
            log.info(e.getMessage());
            requestContext.abortWith(unauthorized(e));
        }
    }

    private Response unauthorized(Exception e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
}
