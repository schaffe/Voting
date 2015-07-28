package ua.dzidzoiev.vote.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import ua.dzidzoiev.vote.security.rest.AuthToken;
import ua.dzidzoiev.vote.service.SecurityTestService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Created by midnight coder on 28-Jul-15.
 */
@Path("/test")
@Produces(TEXT_PLAIN)
@Consumes(APPLICATION_JSON)
@Api(value = "TEST")
public class TestResource {

    @Inject
    SecurityTestService testService;

    @GET
    @Path("/public")
    @ApiOperation("public")
    public String pub() {
        return testService.publicMethod();
    }

    @GET
    @Path("/admin")
    @ApiOperation("admin")
    @AuthToken
    public String adminMethod() {
        return testService.adminMethod();
    }

    @GET
    @Path("/secured")
    @ApiOperation("secured")
    @AuthToken
    public String loggedInMethod() {
        return testService.loggedInMethod();
    }

    @GET
    @Path("/stats")
    @ApiOperation("stats")
    @AuthToken
    public String statsMethod() {
        return testService.statsMethod();
    }
}
