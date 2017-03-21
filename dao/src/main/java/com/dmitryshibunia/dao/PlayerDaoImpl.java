package com.dmitryshibunia.dao;

import com.dmitryshibunia.model.Player;
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
import java.time.LocalDate;
import java.util.List;

/*
*   PlayerDao implementation for H2 database JDBC driver
 */

public class PlayerDaoImpl implements PlayerDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${getAllPlayers.sql}")
    private String GET_ALL_PLAYERS_SQL;
    @Value("${getPlayersById.sql}")
    private String GET_PLAYER_BY_ID_SQL;
    @Value("${addPlayer.sql}")
    private String ADD_PLAYER_SQL;
    @Value("${updatePlayer.sql}")
    private String UPDATE_PLAYER_SQL;
    @Value("${deletePlayer.sql}")
    private String DELETE_PLAYER_SQL;
    @Value("${countPlayersInTeam.sql}")
    private String COUNT_PLAYERS_IN_TEAM_SQL;
    @Value("${filterPlayersByPeriod.sql}")
    private String FILTER_PLAYERS_BY_PERIOD_SQL;
    @Value("${getAllPlayersInteam.sql}")
    private String GET_ALL_PLAYERS_IN_TEAM_SQL;
    @Value("${searchPlayer.sql}")
    private String SEARCH_PLAYER_SQL;

    public PlayerDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<Player> getAllPlayers() {
        LOGGER.debug("DAO method getAllPlayers() ");
        return jdbcTemplate.query(GET_ALL_PLAYERS_SQL,new PlayerRowMapper());
    }


    @Override
    public List<Player> getAllPlayersInTeam(Integer teamId) {
        LOGGER.debug("DAO method getAllPlayersInTeam() ");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("team_id", teamId);
        return namedParameterJdbcTemplate.query(GET_ALL_PLAYERS_IN_TEAM_SQL, sqlParameterSource, new PlayerRowMapper());
    }

    @Override
    public Player getPlayerById(Integer id) {
        LOGGER.debug("DAO method getPlayerById() player id = " + id);
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Player player = namedParameterJdbcTemplate.queryForObject(
                GET_PLAYER_BY_ID_SQL, namedParameters, new PlayerRowMapper());
        return player;
    }

    @Override
    public Integer addPlayer(Player player) {
        LOGGER.debug("DAO method addPlayer() " + player);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("team_id", player.getTeamId());
        parameterSource.addValue("player_name", player.getName());
        parameterSource.addValue("player_surname", player.getSurname());
        parameterSource.addValue("acceptance_date", player.getAcceptanceDate());
        namedParameterJdbcTemplate.update(ADD_PLAYER_SQL, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int updatePlayer(Player player) {
        LOGGER.debug("DAO method updatePlayer() " + player);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("player_id", player.getId());
        parameterSource.addValue("team_id", player.getId());
        parameterSource.addValue("player_name", player.getName());
        parameterSource.addValue("player_surname", player.getSurname());
        parameterSource.addValue("acceptance_date", player.getAcceptanceDate());

        return namedParameterJdbcTemplate.update(UPDATE_PLAYER_SQL, parameterSource);
    }

    @Override
    public int deletePlayer(Integer id) {
        LOGGER.debug("DAO method deletePlayer() player id = " + id);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("player_id",id);
        return namedParameterJdbcTemplate.update(DELETE_PLAYER_SQL, sqlParameterSource);
    }

    @Override
    public int countPlayersInTeam(Integer teamId) {
        LOGGER.debug("DAO method countPlayersInTeam() teamId = " + teamId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("team_id",teamId);
        return namedParameterJdbcTemplate.queryForObject(COUNT_PLAYERS_IN_TEAM_SQL,sqlParameterSource, Integer.class);
    }

    @Override
    public List<Player> filterPlayersByAcceptanceDate(Integer teamId, LocalDate from, LocalDate to) {
        LOGGER.debug("DAO method filterPlayersByAcceptanceDate() from = " + from + " to " + to);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("team_id", teamId);
        parameterSource.addValue("from", from);
        parameterSource.addValue("to", to);

        return  namedParameterJdbcTemplate.query(FILTER_PLAYERS_BY_PERIOD_SQL, parameterSource, new PlayerRowMapper());
    }

    @Override
    public int searchPlayer(String name, String surname){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("player_name", name);
        parameterSource.addValue("player_surname", surname);
        return namedParameterJdbcTemplate.queryForObject(SEARCH_PLAYER_SQL, parameterSource, Integer.class);
    }

    private class PlayerRowMapper implements RowMapper<Player> {
        @Override
        public Player mapRow(ResultSet resultSet, int i) throws SQLException {
            Player player = new Player(
                    resultSet.getInt("player_id"),
                    resultSet.getInt("team_id"),
                    resultSet.getString("player_name"),
                    resultSet.getString("player_surname"),
                    LocalDate.parse(resultSet.getString("acceptance_date"))
            );
            return player;
        }
    }
}
