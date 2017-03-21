package com.dmitryshibunia.service;

import com.dmitryshibunia.model.Player;

import java.time.LocalDate;
import java.util.List;

public interface PlayerService {

    /**
     * Get all players list
     *
     * @param teamId team identifier
     *
     * @return all players list
     */
    List<Player> getAllPlayersInTeam(Integer teamId);

    /**
     * Get player by id
     *
     * @param id player's id
     *
     * @return player object
     */
    Player getPlayerById(Integer id);

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
    void updatePlayer(Player player);

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
     * @param teamId team identifier
     * @param from beginning of period
     * @param to end of period
     *
     * @return filtered players list
     */
    List<Player> filterPlayersByAcceptanceDate(Integer teamId, LocalDate from, LocalDate to);
}
