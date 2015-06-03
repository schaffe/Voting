package ua.dzidzoiev.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by midnight coder on 25-May-15.
 */
@Entity
public class Voter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Long id;

    @Column
    private
    String name;

    @Column
    private
    String surname;

    @Column
    private
    String personalId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "region_id")
    private
    Region region;

    @JsonIgnore
    @OneToOne(mappedBy = "voter")
    private
    Vote vote;

    public Voter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
