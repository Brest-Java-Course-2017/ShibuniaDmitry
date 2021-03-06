package com.dmitryshibunia.dao;

import com.dmitryshibunia.model.Team;
import com.dmitryshibunia.model.TeamDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
*  TeamDao implementation for H2 database JDBC driver
 */


public class TeamDaoImpl implements TeamDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${getAllTeams.sql}")
    private String GET_ALL_TEAMS_SQL;
    @Value("${getTeamById.sql}")
    private String GET_TEAM_BY_ID_SQL;
    @Value("${getTeamByName.sql}")
    private String GET_TEAM_BY_NAME_SQL;
    @Value("${addTeam.sql}")
    private String ADD_TEAM_SQL;
    @Value("${updateTeam.sql}")
    private String UPDATE_TEAM_SQL;
    @Value("${deleteTeam.sql}")
    private String DELETE_TEAM_SQL;

    public TeamDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<TeamDTO> getAllTeams() {
        LOGGER.debug("DAO method getAllTeams() ");
        return jdbcTemplate.query(GET_ALL_TEAMS_SQL,new TeamDORowMapper());

    }

    @Override
    public Team getTeamById(Integer id) {
        LOGGER.debug("DAO method getTeamById() team id = " + id);
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_team_id", id);
        Team team = namedParameterJdbcTemplate.queryForObject(
                GET_TEAM_BY_ID_SQL, namedParameters, new TeamRowMapper());
        return team;
    }

    @Override
    public Team getTeamByName(String name) {
        LOGGER.debug("DAO method getTeamByName() team name = " + name);
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_team_name", name);
        Team team = namedParameterJdbcTemplate.queryForObject(
                GET_TEAM_BY_NAME_SQL, namedParameters, new TeamRowMapper());
        return team;
    }

    @Override
    public Integer addTeam(Team team) {
        LOGGER.debug("DAO method addTeam() " + team);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("team_name", team.getName());
        namedParameterJdbcTemplate.update(ADD_TEAM_SQL, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int updateTeam(Team team) {
        LOGGER.debug("DAO method updateTeam() " + team);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("team_id", team.getId());
        parameterSource.addValue("team_name", team.getName());
        return namedParameterJdbcTemplate.update(UPDATE_TEAM_SQL, parameterSource);
    }

    @Override
    public int deleteTeam(Integer id) {
        LOGGER.debug("DAO method deleteTeam() team id = " + id);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("team_id",id);
        return namedParameterJdbcTemplate.update(DELETE_TEAM_SQL, sqlParameterSource);
    }

    private class TeamRowMapper implements RowMapper<Team> {
        @Override
        public Team mapRow(ResultSet resultSet, int i) throws SQLException {
            Team team = new Team(
                    resultSet.getInt("team_id"),
                    resultSet.getString("team_name")
            );
            return team;
        }
    }

    private class TeamDORowMapper implements RowMapper<TeamDTO> {
        @Override
        public TeamDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            TeamDTO team = new TeamDTO(
                    resultSet.getInt("team_id"),
                    resultSet.getString("team_name"),
                    resultSet.getInt("quantity")
            );
            return team;
        }
    }
}
