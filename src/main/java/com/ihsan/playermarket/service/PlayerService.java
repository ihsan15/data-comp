package com.ihsan.playermarket.service;

import com.ihsan.playermarket.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAll();

    Player get(long playerID);
}
