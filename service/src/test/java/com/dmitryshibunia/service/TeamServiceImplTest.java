package com.dmitryshibunia.service;

import com.dmitryshibunia.model.Team;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class TeamServiceImplTest {

    @Autowired
    TeamService teamService;

    private final Team TEST_TEAM = new Team(1, "Team1");

    @Test
    public void getAllTeams() throws Exception {
        Assert.assertTrue(teamService.getAllTeams().size() == 2);
    }

    @Test
    public void getTeamById() throws Exception {
        Team team = teamService.getTeamById(1);
        Assert.assertTrue(team.equals(TEST_TEAM));
    }

    @Test
    public void getTeamByName() throws Exception {
        Team team = teamService.getTeamByName("Team1");
        Assert.assertTrue(team.equals(TEST_TEAM));
    }

    @Test
    public void addTeam() throws Exception {
        Team team = new Team("team");
        int team_id = teamService.addTeam(team);
        Team addedTeam = teamService.getTeamById(team_id);
        Assert.assertTrue(team.getName().equals(addedTeam.getName()));
        Assert.assertTrue(addedTeam.getId() == team_id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTeamNegative() throws Exception{
        Team team = new Team("Team1");
        teamService.addTeam(team);
    }


    @Test
    public void updateTeam() throws Exception {
        Team team = teamService.getTeamById(1);
        team.setName("New name");
        teamService.updateTeam(team);
        Team updatedTeam = teamService.getTeamById(1);
        Assert.assertTrue(updatedTeam.equals(team));
    }


    @Test(expected = IllegalArgumentException.class)
    public void deleteTeamNegative() throws Exception{
        teamService.deleteTeam(1);
    }

    @Test
    public void deleteTeam() throws Exception {
        Integer id = teamService.addTeam(new Team("team"));
        int startTeamsQuantity = teamService.getAllTeams().size();
        teamService.deleteTeam(id);
        int teamsQuantity = teamService.getAllTeams().size();
        Assert.assertTrue(teamsQuantity == startTeamsQuantity - 1);

    }

}