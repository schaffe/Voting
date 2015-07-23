package ua.dzidzoiev.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by midnight coder on 25-May-15.
 */
@XmlRootElement(name = "region")
@Entity
@Table(name = "regions")
public class Region {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, max = 16)
    private String name;

    @Column
    @NotNull
    @Size(min = 1, max = 4)
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private List<Candidate> candidates;

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private List<Voter> voters;

    public Region() {
    }

    public Region(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Region(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

}
