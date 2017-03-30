package com.dmitryshibunia.rest;

import com.dmitryshibunia.model.Team;
import com.dmitryshibunia.model.TeamDTO;
import com.dmitryshibunia.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TeamController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TeamService teamService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public @ResponseBody List<TeamDTO> getAllTeams() {
        LOGGER.debug("getAllTeams()");
        return teamService.getAllTeams();
    }

    @RequestMapping(value = "/team", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addTeam(@RequestBody Team team){
        LOGGER.debug("addTeam() team = " + team);
        return teamService.addTeam(team);
    }

    @RequestMapping(value = "/team", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody void updateTeam(@RequestBody Team team){
        LOGGER.debug("updateTeam() team = " + team);
        teamService.updateTeam(team);
    }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void deleteTeam(@PathVariable(value = "id") Integer id){
        LOGGER.debug("deleteTeam() id = " + id);
        teamService.deleteTeam(id);
    }

}
