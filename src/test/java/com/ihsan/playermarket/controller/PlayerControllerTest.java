package com.ihsan.playermarket.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.ihsan.playermarket.datahelper.EntityFactory;
import com.ihsan.playermarket.entity.Player;
import com.ihsan.playermarket.service.PlayerService;

import static org.junit.Assert.assertEquals;

public class PlayerControllerTest {

    private PlayerService playerService;

    private PlayerController playerController;

    private EntityFactory entityFactory = new EntityFactory();

    @Before
    public void setup(){
        playerService = Mockito.mock(PlayerService.class);
        playerController = new PlayerController(playerService);
    }

    @Test
    public void listPlayers() {
        Player player = entityFactory.player();
        List<Player> players = new ArrayList<>();
        players.add(player);

        ResponseEntity<List<Player>> actual = ResponseEntity.ok(players);
        Mockito.when(playerService.getAll()).thenReturn(players);

        ResponseEntity<List<Player>> expected = playerController.listPlayers();

        assertEquals(expected, actual);
    }

    @Test
    public void getPlayer() {
        Player player = entityFactory.player();

        ResponseEntity<Player> actual = ResponseEntity.ok(player);
        Mockito.when(playerService.get(Mockito.anyLong())).thenReturn(player);

        ResponseEntity<Player> expected = playerController.getPlayer(player.getId());

        assertEquals(expected, actual);
    }
}