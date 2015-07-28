package ua.dzidzoiev.vote.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.spi.validation.ValidateRequest;
import ua.dzidzoiev.vote.model.Voter;
import ua.dzidzoiev.vote.model.dto.AccountRegistration;
import ua.dzidzoiev.vote.model.dto.auth.AuthLoginElement;
import ua.dzidzoiev.vote.security.rest.AuthToken;
import ua.dzidzoiev.vote.service.AccountService;
import ua.dzidzoiev.vote.util.MessageBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_OK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by midnight coder on 21-Jul-15.
 */
@Path("/account")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Api(value = "account", description = "Account resource")
public class AccountResource {

    @Inject
    AccountService accountService;

    @GET
    @ApiOperation(value = "Get current account data", notes = "")
    @AuthToken
    public Response getCurrentAccount() {
        Voter voter = accountService.getCurrentVoter();
        return Response.ok(voter).build();
    }

    @GET
    @ApiOperation(value = "Get account data", notes = "")
    @AuthToken
    public Response getAccount(String username) {
        Voter voter = accountService.getVoter(username);
        return Response.ok(voter).build();
    }

    @POST
    @NoCache
    @ApiOperation(value = "Register new voter", notes = "")
    @ApiResponses({
            @ApiResponse(code = HTTP_OK, message = "Success"),
            @ApiResponse(code = HTTP_CONFLICT, message = "User with this id exists")
    })
    @ValidateRequest
    public Response registerAccount(@Valid AccountRegistration payload) {
        AuthLoginElement authLoginElement = accountService.registerVoter(payload);
        return Response.ok(authLoginElement).build();
    }

    @PUT
    @ApiOperation(value = "Update current", notes = "")
    @AuthToken
    @ValidateRequest
    public Response updateAccount(@Valid AccountRegistration payload) {
//        accountService.
        return MessageBuilder.notImplemented().build();

    }

    @DELETE
    @ApiOperation(value = "Delete account", notes = "")
    @AuthToken
    public Response deleteAccount() {
        accountService.removeVoterSelf();
        return MessageBuilder.ok().build();
    }

    @PUT
    @Path("/enable")
    public Response enableAccount() {
        return MessageBuilder.notImplemented().build();
    }

    @PUT
    @Path("/disable")
    public Response disableAccount() {
        return MessageBuilder.notImplemented().build();
    }
}
