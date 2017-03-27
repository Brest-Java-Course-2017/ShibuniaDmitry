package com.dmitryshibunia.rest;

import com.dmitryshibunia.model.Player;
import com.dmitryshibunia.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class PlayerController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    PlayerService playerService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    @RequestMapping(value = "/team/{id}/players", method = RequestMethod.GET)
    public @ResponseBody List<Player> getAllPlayersInTeam(@PathVariable(value = "id") Integer id){
        LOGGER.debug("getAllPlayersInTeam() teamId = " + id);
        return playerService.getAllPlayersInTeam(id);
    }

    @RequestMapping(value = "/team/{id}/players/{from}/{to}", method = RequestMethod.GET)
    public @ResponseBody List<Player> filterPlayersByAcceptanceDate(@PathVariable(value = "id") Integer id,
                                                                    @PathVariable(value = "from") String from,
                                                                    @PathVariable(value = "to") String to){
        LOGGER.debug("filterPlayersByAcceptanceDate() teamId = " + id + " from " + from + " to " + to);
        return playerService.filterPlayersByAcceptanceDate(id, LocalDate.parse(from), LocalDate.parse(to));
    }

    @RequestMapping(value = "team/player", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addPlayer(@RequestBody Player player){
        LOGGER.debug("addPlayer() player = " + player);
        return playerService.addPlayer(player);
    }

    @RequestMapping(value = "team/player", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody void updatePlayer(@RequestBody Player player){
        LOGGER.debug("updatePlayer() player = " + player);
        playerService.updatePlayer(player);
    }

    @RequestMapping(value = "team/player/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void deletePlayer(@PathVariable(value = "id") Integer id){
        LOGGER.debug("deletePlayer() id = " + id);
        playerService.deletePlayer(id);
    }

    @RequestMapping(value = "team/{teamId}/count", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Integer countPlayersInTeam(@PathVariable(value = "teamId") Integer id){
        LOGGER.debug("countPlayersInTeam() teamId = " + id);
        return playerService.countPlayersInTeam(id);
    }


}
