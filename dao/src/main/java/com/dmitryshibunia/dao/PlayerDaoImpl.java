package com.dmitryshibunia.dao;

import com.dmitryshibunia.dao.PlayerDao;
import com.dmitryshibunia.model.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by dimgo on 7.3.17.
 */
public class PlayerDaoImpl implements PlayerDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PlayerDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    @Override
    public Player getPlayerById(Integer id) {
        return null;
    }

    @Override
    public Player getPlayerById(String name) {
        return null;
    }

    @Override
    public Integer addPlayer(Player player) {
        return null;
    }

    @Override
    public void udatePlayer(Player player) {

    }

    @Override
    public void deletePlayer(Integer id) {

    }

    @Override
    public int countPlayersInTeam(Integer teamId) {
        return 0;
    }

    @Override
    public List<Player> filterPlayersByAcceptanceDate(LocalDate from, LocalDate to) {
        return null;
    }
}
