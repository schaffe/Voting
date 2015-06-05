package ua.dzidzoiev.vote.rest;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.jboss.ejb3.annotation.SecurityDomain;
import ua.dzidzoiev.vote.model.dto.auth.AuthLoginElement;

/**
 * Simple secured EJB using EJB security annotations.
 *
 * Annotate this EJB for authorization. Allow only those in the "guest" role. For EJB authorization, you must also specify the
 * security domain. This example uses the "other" security domain which is provided by default in the standalone.xml file.
 *
 * @author Sherif Makary
 */
@Stateless
//@RolesAllowed({ "guest", "admin" })
//@SecurityDomain("test-policy")
//@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SecuredEJB {

    // Inject the Session Context
    @Resource
    private SessionContext ctx;

    @Context
    private HttpServletRequest httpRequest;

    /**
     * Secured EJB method using security annotations
     */
    @GET
    @Consumes("*/*")
    @PermitAll
    public String getSecurityInfo() {
        // Session context injected using the resource annotation
        Principal principal = ctx.getCallerPrincipal();

        return principal.getName();
    }

    @POST
    @PermitAll
    public Response login(AuthLoginElement al) {
        try {
            httpRequest.login(al.getUsername(), al.getPassword());
            return Response.status(Response.Status.OK).build();
        } catch (ServletException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }
}