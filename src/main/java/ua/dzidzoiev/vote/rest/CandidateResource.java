package ua.dzidzoiev.vote.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import ua.dzidzoiev.vote.data.CandidateRepository;
import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.security.rest.AuthToken;
import ua.dzidzoiev.vote.service.CandidateService;
import ua.dzidzoiev.vote.service.VotingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by midnight coder on 26-May-15.
 */
@Path("/region/{region-code}/candidate")
@Consumes("*/*")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Api(value = "/region/{region-code}/candidate", description = "Candidate resource to specific region")
public class CandidateResource {

    @Inject
    private Logger log;

    @Inject
    private VotingService service;

    @Inject
    private CandidateService candidateService;

    @GET
    @Path("/{reg-id}")
    @ApiOperation(value = "{reg-id}", notes = "Get candidate data by it`s ID")
    public Candidate lookupCandidateById(@PathParam("reg-id") String id) {
        Candidate candidate = candidateService.findById(id);
        if (candidate == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return candidate;
    }

    @GET
    @ApiOperation(value = "", notes = "Get all candidates in the region")
    public List<Candidate> listAll(@PathParam("region-code") String code) {
        return candidateService.getAllCandidatesInRegion(code);
    }

    @POST
    @Path("/{reg-id}/vote")
    @AuthToken
    @ApiOperation(value = "{reg-id}/vote", notes = "Vote on some candidate. Note that vote can be made only once")
    public Response vote(@PathParam("reg-id") String candidateRegistrationId) {
        service.vote(candidateRegistrationId);
        return null;
    }

}
