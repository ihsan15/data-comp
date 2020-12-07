package com.ihsan.playermarket.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Player implements Serializable {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String nameSurname;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate retirementDate;

    @Column(precision = 24, scale = 8)
    private BigDecimal salary;

    @Column(length = 2)
    private String nationality;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private Set<TeamPlayer> teamPlayers = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(LocalDate retirementDate) {
        this.retirementDate = retirementDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Set<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(Set<TeamPlayer> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
