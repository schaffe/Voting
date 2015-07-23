package ua.dzidzoiev.vote.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.jboss.resteasy.annotations.cache.NoCache;
import ua.dzidzoiev.vote.model.dto.auth.AuthLoginElement;
import ua.dzidzoiev.vote.security.AuthenticationService;
import ua.dzidzoiev.vote.security.rest.AuthToken;
import ua.dzidzoiev.vote.util.MessageBuilder;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import static java.net.HttpURLConnection.*;
import static javax.ws.rs.core.MediaType.*;

@Path("auth")
@Produces(APPLICATION_JSON)
@Api(value = "auth", description = "Authentication resource")
public class AuthResource {

    @Inject
    AuthenticationService authService;

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @NoCache
    @ApiOperation(value = "Login with Form", notes = "Authenticate user with login and password passed through form")
    @ApiResponses({
            @ApiResponse(code = HTTP_OK, message = "Success"),
            @ApiResponse(code = HTTP_UNAUTHORIZED, message = "Problem matching username and password")})
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password) {

        try {
            String authToken = authService.authenticateWithPassword(username, password);
            return MessageBuilder.ok().token(authToken).build();
        } catch (final LoginException e) {
            return MessageBuilder.authenticationRequired().message("Problem matching username and password").build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @NoCache
    @ApiOperation(value = "Login with JSON", notes = "Authenticate user with login and password passed through JSON")
    @ApiResponses({
            @ApiResponse(code = HTTP_OK, message = "Success"),
            @ApiResponse(code = HTTP_UNAUTHORIZED, message = "Problem matching username and password")})
    public Response loginWithJson(AuthLoginElement payload) {

        try {
            String authToken = authService.authenticateWithPassword(payload.getUsername(), payload.getPassword());
            return MessageBuilder.ok().token(authToken).build();
        } catch (final LoginException e) {
            return MessageBuilder.authenticationRequired().message("Problem matching username and password").build();
        }
    }

    @DELETE
    @AuthToken
    @NoCache
    @ApiOperation(value = "Logout", notes = "Logout user and invalidate token")
    @ApiResponses({
            @ApiResponse(code = HTTP_NO_CONTENT, message = "Success"),
            @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Unexpected error occurred")})
    public Response logout(
            @Context HttpHeaders httpHeaders) {

        try {
            authService.logout();
            return MessageBuilder.noContent().build();
        } catch (Exception e) {
            return MessageBuilder.internalServerError().message(e.getMessage()).build();
        }
    }
}