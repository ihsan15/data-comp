package com.ihsan.playermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ihsan.playermarket.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {
}
