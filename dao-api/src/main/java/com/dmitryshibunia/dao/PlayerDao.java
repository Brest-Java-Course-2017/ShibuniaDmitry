package com.dmitryshibunia.dao;

import com.dmitryshibunia.model.Player;

import java.time.LocalDate;
import java.util.List;

/**
 * PlayerDAO interface
 */
public interface PlayerDao {
    /**
     * Get all players list
     *
     * @return all players list
     */
    List<Player> getAllPlayers();

    /**
     * Get player by id
     *
     * @param id player's id
     *
     * @return player object
     */
    Player getPlayerById(Integer id);

    /**
     * Get player by name
     *
     * @param name player's name
     *
     * @return player object
     */
    Player getPlayerById(String name);

    /**
     * add new player
     *
     * @param player player object
     *
     * @return player id
     */
    Integer addPlayer(Player player);

    /**
     * update player
     *
     * @param player player object
     */
    void udatePlayer(Player player);

    /**
     * delete player
     *
     * @param id player identifier
     */
    void deletePlayer(Integer id);

    /**
     * count players quantity in team
     *
     * @param teamId team identifier
     *
     * @return players quantity
     */
    int countPlayersInTeam(Integer teamId);

    /**
     * Get list of players filtered by acceptance date
     *
     * @param from begining of period
     * @param to end of period
     *
     * @return filtered players list
     */
    List<Player> filterPlayersByAcceptanceDate(LocalDate from, LocalDate to);

}
