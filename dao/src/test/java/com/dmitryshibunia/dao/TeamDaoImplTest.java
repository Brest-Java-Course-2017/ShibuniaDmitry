package com.dmitryshibunia.dao;

import com.dmitryshibunia.model.Team;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-config.xml"})
@Transactional
public class TeamDaoImplTest {

    private final Team TEST_TEAM = new Team(1, "Team1");
    @Autowired
    TeamDao teamDao;

    @Test
    public void getAllTeams() throws Exception {
        List<Team> teams = teamDao.getAllTeams();
        Assert.assertTrue(teams.size() == 2);
    }

    @Test
    public void getTeamById() throws Exception {
        Team team = teamDao.getTeamById(1);
        Assert.assertTrue(team.equals(TEST_TEAM));
    }

    @Test
    public void getTeamByName() throws Exception {
        Team team = teamDao.getTeamByName("Team1");
        Assert.assertTrue(team.equals(TEST_TEAM));
    }

    @Test
    public void addTeam() throws Exception {
        Team team = new Team("Team3");
        int teamsQuantityBefore = teamDao.getAllTeams().size();

        Assert.assertNotNull(team);
        Assert.assertNotNull(team.getName());
        Assert.assertNull(team.getId());

        int newTeamId = teamDao.addTeam(team);
        Assert.assertTrue(newTeamId == 3);
        Assert.assertTrue(teamDao.getAllTeams().size() == teamsQuantityBefore + 1);
    }

    @Test
    public void updateTeam() throws Exception {
        Team team = teamDao.getTeamById(1);
        team.setName("Team666");
        teamDao.updateTeam(team);
        Team updatedTeam = teamDao.getTeamById(1);
        Assert.assertTrue(team.equals(updatedTeam));
    }

    @Test
    public void deleteTeam() throws Exception {
        Team team = new Team("Team 3");
        int teamId = teamDao.addTeam(team);
        teamDao.deleteTeam(teamId);
        Assert.assertTrue(teamDao.getAllTeams().size() == 2);
    }

}