package com.ihsan.playermarket.service;

import com.ihsan.playermarket.controller.request.InsertTeamRequest;
import com.ihsan.playermarket.datahelper.EntityFactory;
import com.ihsan.playermarket.entity.Team;
import com.ihsan.playermarket.repository.TeamRepository;
import com.ihsan.playermarket.service.impl.TeamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    private TeamRepository teamRepository;

    private TeamService teamService;

    private EntityFactory entityFactory = new EntityFactory();

    private InsertTeamRequest request;

    @Before
    public void setup(){
        teamRepository = Mockito.mock(TeamRepository.class);
        teamService = new TeamServiceImpl(teamRepository);

        request = entityFactory.insertTeamRequest();
    }

    @Test
    public void addTeam() {
        Team team = entityFactory.team();

        Mockito.when(teamRepository.save(Mockito.any())).thenReturn(team);

        long expected = teamService.addTeam(request);

        assertEquals(expected, team.getId());
    }

    @Test
    public void updateTeam() {
        Team team = entityFactory.team();
        Mockito.when(teamRepository.save(team)).thenReturn(team);

        Optional<Team> optionalTeam = Optional.of(team);
        Mockito.when(teamRepository.findById(Mockito.anyLong())).thenReturn(optionalTeam);

        long expected = teamService.updateTeam(team.getId(), request);

        assertEquals(expected, team.getId());
    }

    @Test
    public void deleteTeam() {
        Team team = entityFactory.team();
        Optional<Team> optionalTeam = Optional.of(team);
        Mockito.when(teamRepository.findById(Mockito.anyLong())).thenReturn(optionalTeam);

        long expected = teamService.deleteTeam(team.getId());

        assertEquals(expected, team.getId());
    }
}