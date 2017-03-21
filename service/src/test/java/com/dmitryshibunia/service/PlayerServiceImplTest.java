package com.dmitryshibunia.service;

import com.dmitryshibunia.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class PlayerServiceImplTest {

    @Autowired
    PlayerService playerService;

    private final Player TEST_PLAYER = new Player(1,1, "Jack", "Richer", LocalDate.parse("2010-01-01"));
    private final Player INVALID_PLAYER = new Player(1, "Jack", "Richer", LocalDate.parse("2010-01-01"));

    @Test
    public void getAllPlayersInTeam() throws Exception {
        Assert.assertTrue(playerService.getAllPlayersInTeam(1).size() == 2);
    }

    @Test
    public void getPlayerById() throws Exception {
        Player player = playerService.getPlayerById(1);
        Assert.assertTrue(player.equals(TEST_PLAYER));
    }

    @Test
    public void addPlayer() throws Exception {
        Player newPlayer = new Player(1, "Will", "Turner", LocalDate.parse("2010-01-01"));
        int newId = playerService.addPlayer(newPlayer);
        newPlayer.setId(newId);
        Player player = playerService.getPlayerById(newId);
        Assert.assertTrue(player.equals(newPlayer));
        Assert.assertTrue(player.getId() == newId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicatePlayer() throws Exception{
        playerService.addPlayer(INVALID_PLAYER);
    }

    @Test
    public void updatePlayer() throws Exception {
        Player player = playerService.getPlayerById(1);
        player.setName("Name");
        playerService.updatePlayer(player);
        Player updatedPlayer = playerService.getPlayerById(1);
        Assert.assertTrue(updatedPlayer.equals(player));
    }

    @Test
    public void deletePlayer() throws Exception {
        int startPlayersQuantity = playerService.getAllPlayersInTeam(1).size();
        playerService.deletePlayer(1);
        int playersQuantity = playerService.getAllPlayersInTeam(1).size();
        Assert.assertTrue(playersQuantity == startPlayersQuantity - 1);
    }

    @Test
    public void countPlayersInTeam() throws Exception {
        Assert.assertTrue(playerService.countPlayersInTeam(1) == 2);
    }

    @Test
    public void filterPlayersByAcceptanceDate() throws Exception {
        List<Player> players = playerService.filterPlayersByAcceptanceDate(1, LocalDate.parse("2014-02-01"), LocalDate.parse("2015-01-01"));
        Assert.assertTrue(players.size() == 1);

    }

}