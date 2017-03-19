package com.dmitryshibunia.service;

import com.dmitryshibunia.model.Team;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml"})
@Transactional
public class TeamServiceImplTest {

    @Autowired
    TeamService teamService;

    @Test
    public void getAllTeams() throws Exception {
        Assert.assertTrue(teamService.getAllTeams().size() == 2);
    }

    @Test
    public void getTeamById() throws Exception {

    }

    @Test
    public void getTeamByName() throws Exception {

    }

    @Test
    public void addTeam() throws Exception {

    }

    @Test
    public void updateTeam() throws Exception {

    }

    @Test
    public void deleteTeam() throws Exception {

    }

}