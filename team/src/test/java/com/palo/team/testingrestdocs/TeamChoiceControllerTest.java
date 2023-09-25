package com.palo.team.testingrestdocs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.palo.team.controller.TeamChoiceController;
import com.palo.team.entity.TeamChoice;
import com.palo.team.model.TeamChoiceModel;
import com.palo.team.service.TeamChoiceService;

@SpringBootTest
public class TeamChoiceControllerTest {

    @Autowired
    private TeamChoiceController teamChoiceController;

    @Autowired
    private TeamChoiceService teamChoiceService;

    @Test
    public void testAddChoice() {
        
        ResponseEntity<String> result = teamChoiceController.addChoice(1234,"Italian");
        assertEquals("welcome", result);
        
    }

    @Test
    public void testGetChoiceWithException() {
        
        when(teamChoiceService.findAllTeamChoice()).thenReturn(null);
        Model model = mock(Model.class);

        ResponseEntity<String> result = teamChoiceController.getAChoice();

        assertEquals("getChoice", result);
        verify(teamChoiceService, times(1)).findAllTeamChoice();
        verify(model, times(1)).addAttribute("selectedChoice", "Sorry, there are no choices found");
    }

    @Test
    public void testGetChoice() {
        TeamChoice teamChoice = new TeamChoice(1, 1234, "Tesla Italiano");
        List<TeamChoice> tcl =  new ArrayList<TeamChoice>();
        tcl.add(teamChoice);
        when(teamChoiceService.findAllTeamChoice()).thenReturn(tcl);
        Model model = mock(Model.class);

        ResponseEntity<String> result = teamChoiceController.getAChoice();

        assertEquals("getChoice", result);
        verify(teamChoiceService, times(1)).findAllTeamChoice();
        verify(model, times(1)).addAttribute("selectedChoice", "Tesla Italiano");
    }
}