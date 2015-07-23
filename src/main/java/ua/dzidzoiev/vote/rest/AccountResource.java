package ua.dzidzoiev.vote.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.jboss.resteasy.spi.validation.ValidateRequest;
import org.jboss.resteasy.annotations.cache.NoCache;
import ua.dzidzoiev.vote.model.Voter;
import ua.dzidzoiev.vote.model.dto.AccountRegistration;
import ua.dzidzoiev.vote.service.AccountService;
import ua.dzidzoiev.vote.util.MessageBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by midnight coder on 21-Jul-15.
 */
@Path("/user")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Api(value = "user", description = "User resource")
public class AccountResource {

    @Inject
    AccountService accountService;

    @POST
    @NoCache
    @ApiOperation(value = "Register new voter", notes = "")
    @ApiResponses({
            @ApiResponse(code = HTTP_OK, message = "Success"),
            @ApiResponse(code = HTTP_CONFLICT, message = "User with this id exists")
    })
    @ValidateRequest
    public Response registerAccount(@Valid AccountRegistration payload) {
        Voter voter = accountService.registerVoter(payload);
        return MessageBuilder.ok().message("{success.log_in}").build();
    }

    @DELETE
    @Path("/{username}")
    @ApiOperation(value = "Delete account", notes = "")

    public Response deleteAccount(@PathParam("username") String username) {
        accountService.removeVoter(username);
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
