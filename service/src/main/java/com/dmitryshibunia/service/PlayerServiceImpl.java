package com.dmitryshibunia.service;

import com.dmitryshibunia.dao.PlayerDao;
import com.dmitryshibunia.model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;


/**
 * Player service interface implementation
 */
public class PlayerServiceImpl implements PlayerService{

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    PlayerDao playerDao;

    public void setPlayerDao(PlayerDao playerDao){

        this.playerDao = playerDao;
    }

    @Override
    public List<Player> getAllPlayersInTeam(Integer teamId) throws DataAccessException {
        LOGGER.debug("Service method getAllPlayersInTeam() teamId = " + teamId);
        return playerDao.getAllPlayersInTeam(teamId);
    }

    @Override
    public Player getPlayerById(Integer id) throws DataAccessException {
        LOGGER.debug("Service method getPlayerById() id = " + id);
        return playerDao.getPlayerById(id);
    }

    @Override
    public Integer addPlayer(Player player) throws DataAccessException {
        Assert.assertNull(player.getId());
        Assert.assertNotNull(player.getTeamId());
        Assert.assertNotNull(player.getName());
        Assert.assertNotNull(player.getSurname());
        Assert.assertNotNull(player.getAcceptanceDate());
        LOGGER.debug("Service method addPlayer() player = " + player);

        try {
            if(playerDao.searchPlayer(player.getName(), player.getSurname()) != 0) {
                throw new IllegalArgumentException("Player already exists");
            }
        } catch (DataAccessException ex) {

        }
        return playerDao.addPlayer(player);
    }

    @Override
    public void updatePlayer(Player player) throws DataAccessException {
        Assert.assertNotNull(player.getId());
        Assert.assertNotNull(player.getTeamId());
        Assert.assertNotNull(player.getName());
        Assert.assertNotNull(player.getSurname());
        Assert.assertNotNull(player.getAcceptanceDate());

        LOGGER.debug("Service method updatePlayer() player = " + player);
        playerDao.updatePlayer(player);
    }

    @Override
    public void deletePlayer(Integer id) throws DataAccessException {
        LOGGER.debug("Service method deletePlayer() id = " + id);
        playerDao.deletePlayer(id);
    }

    @Override
    public int countPlayersInTeam(Integer teamId) throws DataAccessException {
        LOGGER.debug("Service method countPlayersInTeam() teamId = " + teamId);
        return playerDao.countPlayersInTeam(teamId);
    }

    @Override
    public List<Player> filterPlayersByAcceptanceDate(Integer teamId, LocalDate from, LocalDate to) throws DataAccessException {
        LOGGER.debug("Service method filterPlayersByAcceptanceDate() teamId = " + teamId + " from " + from + " to " + to);
        return playerDao.filterPlayersByAcceptanceDate(teamId, from, to);
    }
}
