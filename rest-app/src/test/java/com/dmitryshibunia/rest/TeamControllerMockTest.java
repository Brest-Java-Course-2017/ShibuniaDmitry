package com.dmitryshibunia.rest;

import com.dmitryshibunia.model.Team;
import com.dmitryshibunia.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mock-test-spring-config.xml"})

public class TeamControllerMockTest {

    @Resource
    private TeamController teamController;

    @Autowired
    TeamService teamService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(teamController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(teamService);
        reset(teamService);
    }

    @Test
    public void getAllTeams() throws Exception {
        expect(teamService.getAllTeams()).andReturn(Arrays.<Team>asList(new Team("Team1")));
        replay(teamService);
        mockMvc.perform(
                get("/teams")
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                        .andExpect(status().isOk());
    }

    @Test
    public void addTeam() throws Exception {
        expect(teamService.addTeam(anyObject(Team.class))).andReturn(4);
        replay(teamService);

        String team = new ObjectMapper().writeValueAsString(new Team ("team"));
        mockMvc.perform(
                post("/team")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(team)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("4"));

    }

    @Test
    public void updateUserTest() throws Exception {
        Team team = new Team(1, "team");
        teamService.updateTeam(team);
        expectLastCall();
        replay(teamService);

        String teamStr = new  ObjectMapper().writeValueAsString(team);

        mockMvc.perform(
                put("/team")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamStr)
        ).andDo(print())
                .andExpect(status().isAccepted());
    }


    @Test
    public void deleteTeam() throws Exception{
        teamService.deleteTeam(1);
        expectLastCall();
        replay(teamService);

        mockMvc.perform(
                delete("/team/" + 1))
                .andDo(print())
                .andExpect(status().isOk());
    }



}