package com.ihsan.playermarket.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ihsan.playermarket.datahelper.EntityFactory;
import com.ihsan.playermarket.entity.Player;
import com.ihsan.playermarket.repository.PlayerRepository;
import com.ihsan.playermarket.service.impl.PlayerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    private PlayerRepository playerRepository;

    private PlayerService service;

    private EntityFactory entityFactory = new EntityFactory();

    @Before
    public void setup(){
        playerRepository = Mockito.mock(PlayerRepository.class);
        service = new PlayerServiceImpl(playerRepository);
    }

    @Test
    public void getAll() {
        Player player = entityFactory.player();
        List<Player> players = new ArrayList<>();
        players.add(player);

        Mockito.when(playerRepository.findAll()).thenReturn(players);

        List<Player> expected = service.getAll();

        assertEquals(players,expected);
    }

    @Test
    public void get() {
        Player player = entityFactory.player();
        Optional<Player> optionalPlayer = Optional.of(player);

        long playerId = player.getId();
        Mockito.when(playerRepository.findById(playerId))
                .thenReturn(optionalPlayer);

        Player expected = service.get(playerId);

        assertEquals(player, expected);

    }
}