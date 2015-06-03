package ua.dzidzoiev.vote.rest;

import ua.dzidzoiev.vote.data.CandidateRepository;
import ua.dzidzoiev.vote.data.RegionRepository;
import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;
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
@Path("/region/{region-id:[0-9][0-9]*}/candidate")
@Consumes("*/*")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CandidateRestService {

    @Inject
    private Logger log;

    @Inject
    VotingService service;

    @Inject
    private CandidateRepository repository;

    @GET
    @Path("/{id:[0-9][0-9]*}")
    public Candidate lookupCandidateById(@PathParam("id") long id) {
        Candidate candidate = repository.findById(id);
        if (candidate == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return candidate;
    }

    @GET
    public List<Candidate> listAll(@PathParam("region-id") long id) {
        return repository.getAllCandidatesInRegion(id);
    }

    @POST
    @Path("/{id:[0-9][0-9]*}/vote")
    public Response vote(@PathParam("id") long candId) {
        service.vote(candId);
        return null;
    }

}
