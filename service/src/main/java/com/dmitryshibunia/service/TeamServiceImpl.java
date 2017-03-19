package com.dmitryshibunia.service;

import com.dmitryshibunia.dao.TeamDao;
import com.dmitryshibunia.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Team service interface implementation
 */
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamDao teamDao;

    public void setTeamDao(TeamDao teamDao){

        this.teamDao = teamDao;
    }
    @Override
    public List<Team> getAllTeams() throws DataAccessException {
        return teamDao.getAllTeams();
    }

    @Override
    public Team getTeamById(Integer id) {
        return null;
    }

    @Override
    public Team getTeamByName(String name) {
        return null;
    }

    @Override
    public Integer addTeam(Team team) {
        return null;
    }

    @Override
    public int updateTeam(Team team) {
        return 0;
    }

    @Override
    public int deleteTeam(Integer id) {
        return 0;
    }
}
