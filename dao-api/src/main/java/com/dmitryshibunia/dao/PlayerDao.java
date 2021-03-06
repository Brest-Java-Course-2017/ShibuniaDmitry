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
    int updatePlayer(Player player);

    /**
     * delete player
     *
     * @param id player identifier
     */
    int deletePlayer(Integer id);

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

    int searchPlayer(String name, String surname);

}
