package com.ihsan.playermarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ihsan.playermarket.controller.request.InsertTeamRequest;
import com.ihsan.playermarket.service.TeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/playermarket/team", consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "api documentation")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping(value = "", consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "insert Team and player")
    public ResponseEntity<Long> insertTeam(@Valid @RequestBody InsertTeamRequest request) {
        return ResponseEntity.ok(teamService.addTeam(request));
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "update Team and player")
    public ResponseEntity<Long> updateTeam(@Valid @PathVariable("id") Long teamId, @Valid @RequestBody InsertTeamRequest request){
        return ResponseEntity.ok(teamService.updateTeam(teamId, request));
    }

    @DeleteMapping(value = "/{id}", consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "delete Team")
    public ResponseEntity<Long> deleteTeam(@Valid @PathVariable("id") Long teamId) {
        return ResponseEntity.ok(teamService.deleteTeam(teamId));
    }
}
