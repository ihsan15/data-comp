package com.ihsan.playermarket.service.impl;

import com.ihsan.playermarket.entity.Player;
import com.ihsan.playermarket.repository.PlayerRepository;
import com.ihsan.playermarket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player get(long playerID) {
        return playerRepository.findById(playerID)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found for this id : " + playerID));
    }
}
