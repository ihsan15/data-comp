package com.ihsan.playermarket.service;

import com.ihsan.playermarket.datahelper.EntityFactory;
import com.ihsan.playermarket.entity.Player;
import com.ihsan.playermarket.entity.Team;
import com.ihsan.playermarket.service.impl.TransferServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.*;

public class TransferServiceTest {

    private TeamService teamService;
    private PlayerService playerService;
    private TransferService transferService;
    private EntityFactory entityFactory = new EntityFactory();

    @Before
    public void setup(){
        teamService = Mockito.mock(TeamService.class);
        playerService = Mockito.mock(PlayerService.class);
        transferService = new TransferServiceImpl(playerService, teamService);
    }

    @Test
    public void calculateTransferAmount() {
        Player player = entityFactory.player();
        Team team = entityFactory.team();

        Mockito.when(playerService.get(Mockito.anyLong())).thenReturn(player);
        Mockito.when(teamService.get(Mockito.anyLong())).thenReturn(team);

        LocalDate today = LocalDate.now();
        int monthOfExperience = Period.between(player.getStartDate(), today).getMonths();
        int age = Period.between(player.getBirthDate(), today).getYears();
        BigDecimal tpAmount = new BigDecimal("100000"); // read by parameter
        BigDecimal commissionRate = new BigDecimal("10"); // read by parameter

        BigDecimal transferAmount = (tpAmount.multiply(new BigDecimal(monthOfExperience))).divide(new BigDecimal(age), RoundingMode.HALF_DOWN);
        BigDecimal commissionAmount = (transferAmount.multiply(commissionRate)).divide(new BigDecimal("100"), RoundingMode.HALF_DOWN);

        BigDecimal contractAmount = transferAmount.add(commissionAmount);

        String actual = "Sözleşme ücreti : " + contractAmount + " " + team.getCurrency();

        assertEquals(transferService.calculateTransferAmount(team.getId(), player.getId()), actual);
    }
}