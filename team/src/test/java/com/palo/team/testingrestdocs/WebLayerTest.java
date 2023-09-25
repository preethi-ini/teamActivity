package com.palo.team.testingrestdocs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.palo.team.controller.TeamChoiceController;
import com.palo.team.entity.TeamChoice;
import com.palo.team.model.TeamChoiceModel;
import com.palo.team.service.TeamChoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class WebLayerTest {

    private TeamChoiceController teamChoiceController;
    private TeamChoiceService teamChoiceService;
    private Model model;

    @BeforeEach
    public void setUp() {
        teamChoiceService = mock(TeamChoiceService.class);
        model = mock(Model.class);
        teamChoiceController = new TeamChoiceController();
        teamChoiceController.setTeamChoiceService(teamChoiceService);
    }
    
    @Test
    public void testAddChoice() {
        TeamChoiceModel teamChoiceModel = new TeamChoiceModel();
        teamChoiceModel.setChoice("Choice 1");
        ResponseEntity<String> result = teamChoiceController.addChoice(1234,"Italian");
        assertEquals("welcome", result.getBody());
        verify(teamChoiceService, times(1)).addChoice(teamChoiceModel);
        verify(model, times(1)).addAttribute("success", "Your choice was saved successfully");
    }

    @Test
    public void testGetChoiceWithException() {
        List<TeamChoice> tcl =  new ArrayList<TeamChoice>();
        when(teamChoiceService.findAllTeamChoice()).thenReturn(tcl);
        ResponseEntity<String> result = teamChoiceController.getAChoice();
        assertEquals("getChoice", result.getBody());
        verify(teamChoiceService, times(1)).findAllTeamChoice();
        verify(model, times(1)).addAttribute("selectedChoice", "Sorry, there are no choices found");
    }

    @Test
    public void testGetChoice() {
        TeamChoice teamChoice = new TeamChoice(1, 1234,"Tesla");
        List<TeamChoice> tcl =  new ArrayList<TeamChoice>();
        tcl.add(teamChoice);
        when(teamChoiceService.findAllTeamChoice()).thenReturn(tcl);
        ResponseEntity<String> result = teamChoiceController.getAChoice();
        assertEquals("getChoice", result.getBody());
        verify(teamChoiceService, times(1)).findAllTeamChoice();
        verify(model, times(1)).addAttribute("selectedChoice", "Tesla");
    }
}