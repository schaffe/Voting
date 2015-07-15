package ua.dzidzoiev.vote.util;

import ua.dzidzoiev.vote.data.CandidateRepository;
import ua.dzidzoiev.vote.data.RegionRepository;
import ua.dzidzoiev.vote.data.VoterRepository;
import ua.dzidzoiev.vote.model.Candidate;
import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.model.Voter;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Initializes application
 * !!!! SET ENCODING TO win-1251
 */
@Startup
@Singleton
@DependsOn("SecurityInitializer")
public class ApplicationInitilizer {

    @Inject
    RegionRepository regionRepository;

    @Inject
    VoterRepository voterRepository;

    @Inject
    CandidateRepository candidateRepository;

    @PostConstruct
    public void initApplication() {
        Region rivne = new Region("г���", "��");
        Region kiev = new Region("Kiev", "KV");
        Region chernihiv = new Region("Chernihiv", "CR");
        Region volun = new Region("������", "��");

        regionRepository.create(rivne);
        regionRepository.create(kiev);
        regionRepository.create(chernihiv);
        regionRepository.create(volun);

        Voter voter = new Voter();
        voter.setLoginName("admin");
        voter.setFirstName("AdminName");
        voter.setSurname("AdminSurname");
        voter.setPersonalId("A3M111N");
        voter.setRegion(kiev);

        voterRepository.create(voter);

        Candidate candidate1 = new Candidate(kiev, "John", "Deere", "Traktorist");
        Candidate candidate2 = new Candidate(kiev, "�������", "�����������","�������� ������ ������, ������ ��������� ������������� ����������� �������� �� ��������. ���� ����.");

        candidateRepository.create(candidate1);
        candidateRepository.create(candidate2);
    }
}
