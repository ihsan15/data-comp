package com.ihsan.playermarket.service.impl;

import com.ihsan.playermarket.controller.request.InsertTeamRequest;
import com.ihsan.playermarket.entity.Team;
import com.ihsan.playermarket.repository.TeamRepository;
import com.ihsan.playermarket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamServiceImpl  implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Override
    public Long addTeam(InsertTeamRequest request) {
        Team team = request.getTeam();
        team.getTeamPlayers().forEach(teamPlayer -> teamPlayer.setTeam(team));
        teamRepository.save(team);
        return team.getId();
    }

    @Override
    public Team get(long teamId) {
        return getTeam(teamId);
    }

    @Override
    public Long updateTeam(long teamId, InsertTeamRequest request) {
        Team updatedTeam = request.getTeam();

        Team team = getTeam(teamId);

        team.setLeague(updatedTeam.getLeague());
        team.setStadium(updatedTeam.getStadium());
        team.setTeamName(updatedTeam.getTeamName());
        team.setTeamPlayers(updatedTeam.getTeamPlayers());
        team.getTeamPlayers().forEach(teamPlayer -> teamPlayer.setTeam(team));
        teamRepository.save(team);
        return team.getId();
    }

    private Team getTeam(long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found for this id : " + teamId));
    }

    @Override
    public Long deleteTeam(long teamId) {
        Team team = getTeam(teamId);

        teamRepository.delete(team);
        return teamId;
    }
}
