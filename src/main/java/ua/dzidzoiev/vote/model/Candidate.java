package ua.dzidzoiev.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by midnight coder on 25-May-15.
 */
@Entity
@Table(name = "candidates")
public class Candidate {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    @Column
    @Size(min = 1, max = 16)
    @NotNull
    private String name;

    @Column
    @Size(min = 1, max = 16)
    @NotNull
    private String surname;

    @Column (columnDefinition = "TEXT")
    @Size(max = 2048)
    private String description;

    @Column
    @Size(max = 16)
    @NotNull
    private String registrationId;

    public Candidate() {
    }

    public Candidate(Region region, String name, String surname, String description, String registrationId) {
        this.region = region;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.registrationId = registrationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
