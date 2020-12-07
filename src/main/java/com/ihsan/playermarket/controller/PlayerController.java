package com.ihsan.playermarket.controller;

import com.ihsan.playermarket.entity.Player;
import com.ihsan.playermarket.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/playermarket/player", consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "api documentation")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "", consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "list all players")
    public ResponseEntity<List<Player>> listPlayers(){
        return ResponseEntity.ok(playerService.getAll());
    }

    @GetMapping(value = "/{id}", consumes = {MediaType.ALL_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "list all players")
    public ResponseEntity<Player> getPlayer(@Valid @PathVariable("id") Long playerId){
        return ResponseEntity.ok(playerService.get(playerId));
    }
}
