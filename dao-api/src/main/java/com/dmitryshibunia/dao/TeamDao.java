package com.dmitryshibunia.dao;

import com.dmitryshibunia.model.Team;
import com.dmitryshibunia.model.TeamDTO;

import java.util.List;

/**
 * TeamDao interface
 */
public interface TeamDao {

    /**
     * Get all teams list
     *
     * @return all teams list
     */
    List<TeamDTO> getAllTeams();

    /**
     * Get team by id
     *
     * @param id team identifier
     *
     * @return team object
     */
    Team getTeamById(Integer id);

    /**
     * Get team by name
     *
     * @param name team name
     *
     * @return team object
     */
    Team getTeamByName(String name);

    /**
     * add new team
     *
     * @param team team object
     *
     * @return team id
     */
    Integer addTeam(Team team);

    /**
     * update team
     *
     * @param team team object
     */
    int updateTeam(Team team);

    /**
     * delete team
     *
     * @param id team identifier
     */
    int deleteTeam(Integer id);
}
