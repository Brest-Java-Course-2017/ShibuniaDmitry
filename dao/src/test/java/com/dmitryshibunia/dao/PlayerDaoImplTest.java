package com.dmitryshibunia.dao;

import com.dmitryshibunia.model.Player;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-config.xml"})
@Transactional
public class PlayerDaoImplTest {

    private final Player TEST_PLAYER = new Player(1,1, "Jack", "Richer", LocalDate.parse("2010-01-01"));

    @Autowired
    PlayerDao playerDao;
    @Test
    public void getAllPlayers() throws Exception {
        List<Player> players = playerDao.getAllPlayers();
        Assert.assertTrue(players.size() == 3);
    }

    @Test
    public void getPlayerById() throws Exception {
        Player player = playerDao.getPlayerById(1);
        Assert.assertTrue(player.equals(TEST_PLAYER));
    }

    @Test
    public void getAllPlayersInTeam(){
        List<Player> players = playerDao.getAllPlayersInTeam(1);
        Assert.assertNotNull(players);
    }

    @Test
    public void addPlayer() throws Exception {

        Player player = new Player(1, "Name", "Surname", LocalDate.parse("2000-10-10"));
        int teamsQuantityBefore = playerDao.getAllPlayers().size();

        Assert.assertNotNull(player);
        Assert.assertNotNull(player.getName());
        Assert.assertNotNull(player.getSurname());
        Assert.assertNotNull(player.getTeamId());
        Assert.assertNull(player.getId());

        int newPlayerId = playerDao.addPlayer(player);
        Assert.assertTrue(newPlayerId == 4);
        Assert.assertTrue(playerDao.getAllPlayers().size() == teamsQuantityBefore + 1);

    }

    @Test
    public void updatePlayer() throws Exception {
        Player player = new Player(1,1, "Name", "Surname", LocalDate.parse("2000-10-10"));

        Assert.assertNotNull(player);
        Assert.assertNotNull(player.getName());
        Assert.assertNotNull(player.getSurname());
        Assert.assertNotNull(player.getTeamId());
        Assert.assertNotNull(player.getId());

        playerDao.updatePlayer(player);
        Player updatedPlayer = playerDao.getPlayerById(1);
        Assert.assertTrue(updatedPlayer.equals(player));
    }

    @Test
    public void deletePlayer() throws Exception {
        int usersQuantityBefore = playerDao.getAllPlayers().size();
        playerDao.deletePlayer(1);
        Assert.assertTrue(playerDao.getAllPlayers().size() == usersQuantityBefore - 1);
    }

    @Test
    public void countPlayersInTeam() throws Exception {
        Assert.assertTrue(playerDao.countPlayersInTeam(1) == 2);
    }

    @Test
    public void filterPlayersByAcceptanceDate() throws Exception {
        List<Player> players = playerDao.filterPlayersByAcceptanceDate(1, LocalDate.parse("2014-02-01"), LocalDate.parse("2015-01-01"));
        Assert.assertTrue(players.size() == 1);
    }

    @Test
    public void searchPlayer() throws Exception{
        Assert.assertTrue(playerDao.searchPlayer("Jack", "Richer") == 1);
    }

}