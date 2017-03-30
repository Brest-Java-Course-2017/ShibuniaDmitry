package com.dmitryshibunia.service;

import com.dmitryshibunia.dao.PlayerDao;
import com.dmitryshibunia.dao.TeamDao;
import com.dmitryshibunia.model.Team;
import com.dmitryshibunia.model.TeamDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Team service interface implementation
 */
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamDao teamDao;

    @Autowired
    PlayerDao playerDao;

    private static final Logger LOGGER = LogManager.getLogger();

    public void setPlayerDao(PlayerDao playerDao){

        this.playerDao = playerDao;
    }

    public void setTeamDao(TeamDao teamDao){

        this.teamDao = teamDao;
    }
    @Override
    public List<TeamDTO> getAllTeams() throws DataAccessException {
        LOGGER.debug("Service method getAllTeams() ");
        return teamDao.getAllTeams();
    }

    @Override
    public Team getTeamById(Integer id) throws DataAccessException{
        Assert.assertNotNull(id);
        LOGGER.debug("Service method getTeamById() id = " + id);
        return teamDao.getTeamById(id);
    }

    @Override
    public Team getTeamByName(String name) throws DataAccessException{
        Assert.assertNotNull(name);
        LOGGER.debug("Service method getTeamByName() name = " + name);
        return teamDao.getTeamByName(name);
    }

    @Override
    public Integer addTeam(Team team) throws DataAccessException{
        Assert.assertNotNull(team.getName());
        Assert.assertNull(team.getId());
        LOGGER.debug("Service method addTeam() team = " + team);

        try {
            if (teamDao.getTeamByName(team.getName()) != null) {
                throw new IllegalArgumentException("Team already exists");
            }
        } catch (DataAccessException ex) {

        }

        return teamDao.addTeam(team);
    }

    @Override
    public void updateTeam(Team team) throws DataAccessException{
        Assert.assertNotNull(team.getName());
        Assert.assertNotNull(team.getId());
        LOGGER.debug("Service method updateTeam() team = " + team);
        teamDao.updateTeam(team);
    }

    @Override
    public void deleteTeam(Integer id) throws DataAccessException{
        LOGGER.debug("Service method deleteTeam() id = " + id);
        try {
            if(playerDao.countPlayersInTeam(id) != 0) {
                throw new IllegalArgumentException("This team has players!");
            }
        } catch (DataAccessException ex) {

        }

        teamDao.deleteTeam(id);
    }
}
