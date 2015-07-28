package ua.dzidzoiev.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by midnight coder on 25-May-15.
 */
@Entity
@Table(name = "voters")
@XmlRootElement
public class Voter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String surname;

    @Column(unique = true, nullable = false)
    @NotNull
    @JsonIgnore
    private String personalId;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne(mappedBy = "voter")
    @JsonIgnore
    private Vote vote;

    @Column(unique = true, nullable = false)
    @NotNull
    @JsonIgnore
    private String loginName;

    public Voter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
