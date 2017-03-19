package com.dmitryshibunia.service;

import com.dmitryshibunia.dao.PlayerDao;
import com.dmitryshibunia.model.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * Player service interface implementation
 */
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    PlayerDao playerDao;

    public void setPlayerDao(PlayerDao playerDao){

        this.playerDao = playerDao;
    }
    @Override
    public List<Player> getAllPlayersInTeam(Integer teamId) {
        return null;
    }

    @Override
    public Player getPlayerById(Integer id) {
        return null;
    }

    @Override
    public Integer addPlayer(Player player) {
        return null;
    }

    @Override
    public int updatePlayer(Player player) {
        return 0;
    }

    @Override
    public int deletePlayer(Integer id) {
        return 0;
    }

    @Override
    public int countPlayersInTeam(Integer teamId) {
        return 0;
    }

    @Override
    public List<Player> filterPlayersByAcceptanceDate(Integer teamId, LocalDate from, LocalDate to) {
        return null;
    }
}
