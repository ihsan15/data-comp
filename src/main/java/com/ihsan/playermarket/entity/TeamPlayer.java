package com.ihsan.playermarket.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class TeamPlayer  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    private boolean isActiveTeam;

    @Column(length = 3)
    private String number;

    private LocalDate startDate;

    private LocalDate endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isActiveTeam() {
        return isActiveTeam;
    }

    public void setActiveTeam(boolean activeTeam) {
        isActiveTeam = activeTeam;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
