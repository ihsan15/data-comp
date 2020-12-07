package com.ihsan.playermarket.service.impl;

import com.ihsan.playermarket.entity.Player;
import com.ihsan.playermarket.entity.Team;
import com.ihsan.playermarket.service.PlayerService;
import com.ihsan.playermarket.service.TeamService;
import com.ihsan.playermarket.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    private PlayerService playerService;

    private TeamService teamService;

    @Autowired
    public TransferServiceImpl(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    public String calculateTransferAmount(long teamId, long playerId){
        Player player = playerService.get(playerId);
        Team team = teamService.get(teamId);

        LocalDate today = LocalDate.now();
        int monthOfExperience = Period.between(player.getStartDate(), today).getMonths();
        int age = Period.between(player.getBirthDate(), today).getYears();
        BigDecimal tpAmount = new BigDecimal("100000"); // read by parameter
        BigDecimal commissionRate = new BigDecimal("10"); // read by parameter

        BigDecimal transferAmount = (tpAmount.multiply(new BigDecimal(monthOfExperience))).divide(new BigDecimal(age), RoundingMode.HALF_DOWN);
        BigDecimal commissionAmount = (transferAmount.multiply(commissionRate)).divide(new BigDecimal("100"), RoundingMode.HALF_DOWN);

        BigDecimal contractAmount = transferAmount.add(commissionAmount);

        return "Sözleşme ücreti : " + contractAmount + " " + team.getCurrency();
    }
}
