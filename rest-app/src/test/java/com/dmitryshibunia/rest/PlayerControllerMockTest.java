package com.dmitryshibunia.rest;

import com.dmitryshibunia.model.Player;
import com.dmitryshibunia.model.Team;
import com.dmitryshibunia.service.PlayerService;
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
import java.time.LocalDate;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mock-test-spring-config.xml"})

public class PlayerControllerMockTest {

    @Resource
    private PlayerController playerController;

    @Autowired
    PlayerService playerService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(playerController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(playerService);
        reset(playerService);
    }


    @Test
    public void getAllTeams() throws Exception {
        expect(playerService.getAllPlayersInTeam(1)).andReturn(Arrays.<Player>asList(
                new Player(1, "Jack", "Richer", LocalDate.parse("2010-01-01"))));
        replay(playerService);
        mockMvc.perform(
                get("/team/1/players")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

}