package ua.dzidzoiev.vote.service;

import ua.dzidzoiev.vote.data.RegionRepository;
import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.security.annotations.Admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RegionService {

    @Inject
    private RegionRepository repository;

    public Region findById(Long id) {
        return repository.findById(id);
    }

    @Admin
    public void update(Region c) {
        repository.update(c);
    }

    public List<Region> getAll() {
        return repository.getAll();
    }

    @Admin
    public void remove(Region c) {
        repository.remove(c);
    }

    public Region findByCode(String code) {
        return repository.findByCode(code);
    }

    @Admin
    public void create(Region c) {
        repository.create(c);
    }
}
