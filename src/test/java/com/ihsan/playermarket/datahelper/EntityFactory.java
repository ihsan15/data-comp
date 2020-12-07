package com.ihsan.playermarket.datahelper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.ihsan.playermarket.controller.request.CalculateContractRequest;
import com.ihsan.playermarket.controller.request.InsertTeamRequest;
import com.ihsan.playermarket.entity.Player;
import com.ihsan.playermarket.entity.Team;
import com.ihsan.playermarket.entity.TeamPlayer;

public class EntityFactory {

    public EntityFactory(){

    }

    public CalculateContractRequest calculateContractRequest() {
        CalculateContractRequest request = new CalculateContractRequest();
        request.setPlayerId(1);
        request.setTeamId(1);
        return request;
    }

    public InsertTeamRequest insertTeamRequest() {
        InsertTeamRequest request = new InsertTeamRequest();
        request.setTeam(team());
        return request;
    }

    public Player player(){
        Player player = new Player();
        player.setBirthDate(LocalDate.now().minusYears(18));
        player.setId(1);
        player.setNameSurname("nameSurname");
        player.setRetirementDate(LocalDate.now().plusYears(20));
        player.setSalary(new BigDecimal(1200));
        player.setStartDate(LocalDate.now());
        player.setTeamPlayers(teamPlayers());

        return player;
    }

    private Set<TeamPlayer> teamPlayers() {
        Set<TeamPlayer> teamPlayers = new HashSet<>();
        teamPlayers.add(teamPlayer());
        return teamPlayers;
    }

    private TeamPlayer teamPlayer() {
        TeamPlayer teamPlayer = new TeamPlayer();
        teamPlayer.setTeam(new Team());
        teamPlayer.setActiveTeam(true);
        teamPlayer.setEndDate(LocalDate.now().plusYears(12));
        teamPlayer.setNumber("12");
        teamPlayer.setPlayer(new Player());
        teamPlayer.setId(1);
        return teamPlayer;
    }

    public Team team(){
        Team  team = new Team();
        team.setTeamPlayers(teamPlayers());
        team.setTeamName("teamName");
        team.setStadium("stadium");
        team.setLeague("league");
        team.setEstablishmentDate(LocalDate.now().minusYears(25));
        team.setCurrency("currency");
        team.setId(1);
        team.setNationality("TR");
        return team;
    }
}
