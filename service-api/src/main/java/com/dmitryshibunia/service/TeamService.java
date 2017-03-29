package com.dmitryshibunia.service;

import com.dmitryshibunia.model.Team;
import com.dmitryshibunia.model.TeamDO;

import java.util.List;

public interface TeamService {

    /**
     * Get all teams list
     *
     * @return all teams list
     */
    List<TeamDO> getAllTeams();

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
    void updateTeam(Team team);

    /**
     * delete team
     *
     * @param id team identifier
     */
    void deleteTeam(Integer id);
}
