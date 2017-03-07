package com.dmitryshibunia.dao;

import com.dmitryshibunia.dao.TeamDao;
import com.dmitryshibunia.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by dimgo on 7.3.17.
 */
public class TeamDaoImpl implements TeamDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TeamDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<Team> getAllTeams() {
        return null;
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
    public void udateTeam(Team team) {

    }

    @Override
    public void deleteTeam(Integer id) {

    }
}
