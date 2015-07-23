package ua.dzidzoiev.vote.service;

import org.picketlink.authorization.annotations.RolesAllowed;
import ua.dzidzoiev.vote.data.CandidateRepository;
import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.security.ApplicationRoles;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by midnight coder on 23-Jul-15.
 */
@Stateless
public class CandidateService {

    @Inject
    private CandidateRepository candidateRepository;

    public Candidate findById(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate findById(String regId){
        return candidateRepository.findByRegId(regId);
    }

    @RolesAllowed(ApplicationRoles.ADMIN)
    public void update(Candidate c) {
        candidateRepository.update(c);
    }

    public List<Candidate> getAllCandidatesInRegion(long regionId) {
        return candidateRepository.getAllCandidatesInRegion(regionId);
    }

    public List<Candidate> getAllCandidatesInRegion(String regionCode) {
        return candidateRepository.getAllCandidatesInRegion(regionCode);
    }

    @RolesAllowed(ApplicationRoles.ADMIN)
    public void create(Candidate c) {
        candidateRepository.create(c);
    }

    @RolesAllowed(ApplicationRoles.ADMIN)
    public void remove(Candidate c) {
        candidateRepository.remove(c);
    }


}
