package com.ihsan.playermarket.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Team  implements Serializable {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    @NotBlank(message = "Team name is mandatory")
    private String teamName;

    @Column(nullable = false)
    private LocalDate establishmentDate;

    @Column(length = 100)
    private String stadium;

    @Column(length = 100)
    private String league;

    @Column(length = 2)
    @NotBlank(message = "Nationality is mandatory")
    private String nationality;

    // maybe create nationality table
    @Column(length = 3)
    @NotBlank(message = "Nationality is mandatory")
    private String currency;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST})
    private Set<TeamPlayer> teamPlayers = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Set<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(Set<TeamPlayer> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
