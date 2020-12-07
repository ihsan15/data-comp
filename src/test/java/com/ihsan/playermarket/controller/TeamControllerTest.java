package com.ihsan.playermarket.controller;

import com.ihsan.playermarket.controller.request.InsertTeamRequest;
import com.ihsan.playermarket.datahelper.EntityFactory;
import com.ihsan.playermarket.entity.Team;
import com.ihsan.playermarket.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamControllerTest {

    private TeamService teamService;
    private TeamController teamController;
    private InsertTeamRequest request;
    private EntityFactory entityFactory = new EntityFactory();

    @Before
    public void setup(){
        teamService = Mockito.mock(TeamService.class);
        teamController = new TeamController(teamService);
        request = entityFactory.insertTeamRequest();
    }

    @Test
    public void insertTeam() {
        long id = 1;

        Mockito.when(teamService.addTeam(request)).thenReturn(id);

        ResponseEntity<Long> expected = teamController.insertTeam(request);

        assertEquals(ResponseEntity.ok(id), expected);
    }

    @Test
    public void updateTeam() {
        long id = 1;

        Mockito.when(teamService.updateTeam(id, request)).thenReturn(id);

        ResponseEntity<Long> expected = teamController.updateTeam(id, request);

        assertEquals(ResponseEntity.ok(id), expected);
    }

    @Test
    public void deleteTeam() {
        long id = 1;

        Mockito.when(teamService.deleteTeam(id)).thenReturn(id);

        ResponseEntity<Long> expected = teamController.deleteTeam(id);

        assertEquals(ResponseEntity.ok(id), expected);
    }
}