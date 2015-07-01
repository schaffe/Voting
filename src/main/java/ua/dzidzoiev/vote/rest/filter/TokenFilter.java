package ua.dzidzoiev.vote.rest.filter;

import ua.dzidzoiev.vote.rest.AuthResource;
import ua.dzidzoiev.vote.security.DemoAuthenticator;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by midnight coder on 07-Jun-15.
 */
@Token
@Provider
//@PreMatching
public class TokenFilter implements ContainerRequestFilter{
    @Inject
    DemoAuthenticator demoAuthenticator;

    @Inject
    private Logger log;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.info(String.format("Token filtering %s %s", requestContext.getMethod(), requestContext.getUriInfo().getPath()));

        String serviceKey = requestContext.getHeaderString(AuthResource.SERVICE_KEY);
        log.fine(String.format("Received token %s", serviceKey));

        if (!demoAuthenticator.isServiceKeyValid(serviceKey)) {
            log.fine(String.format("Outcome unsuccessful"));
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        log.fine(String.format("Success"));

    }
}
