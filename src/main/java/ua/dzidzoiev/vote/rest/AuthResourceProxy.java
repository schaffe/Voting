package ua.dzidzoiev.vote.rest;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Local
@Path("auth")
public interface AuthResourceProxy extends Serializable {

    public static final String SERVICE_KEY = "service_key";
    public static final String AUTH_TOKEN = "auth_token";

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @Context HttpHeaders httpHeaders,
            @FormParam("username") String username,
            @FormParam("password") String password);

    @GET
    @Path("demo-get-method")
    @Produces(MediaType.APPLICATION_JSON)
    public Response demoGetMethod();

    @POST
    @Path("demo-post-method")
    @Produces(MediaType.APPLICATION_JSON)
    public Response demoPostMethod();

    @POST
    @Path("logout")
    public Response logout(
            @Context HttpHeaders httpHeaders
    );
}