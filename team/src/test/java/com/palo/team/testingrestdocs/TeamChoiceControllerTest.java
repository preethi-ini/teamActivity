package com.palo.team.testingrestdocs;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import com.palo.team.controller.TeamChoiceController;
import com.palo.team.entity.TeamChoice;
import com.palo.team.service.TeamChoiceService;

@SpringBootTest
public class TeamChoiceControllerTest {

    @Autowired
    private TeamChoiceController teamChoiceController;

    @MockBean
    private TeamChoiceService teamChoiceService;

    @Test
    public void testAddChoice() {
        
        ResponseEntity<String> result = teamChoiceController.addChoice(1234,"Italian");
        assertTrue(result.getBody().toString().contains("Your choice was saved successfully"));
        
    }

    @Test
    public void testGetChoiceWithException() {
        when(teamChoiceService.findAllTeamChoice()).thenReturn(null);
        ResponseEntity<String> result = teamChoiceController.getAChoice();
        assertTrue(result.getBody().toString().contains("Sorry, there was a error in processing your request"));
        verify(teamChoiceService, times(1)).findAllTeamChoice();
    }

    @Test
    public void testGetChoice() {
        TeamChoice teamChoice = new TeamChoice(1, 1234, "Tesla Italiano");
        List<TeamChoice> tcl =  new ArrayList<TeamChoice>();
        tcl.add(teamChoice);
        when(teamChoiceService.findAllTeamChoice()).thenReturn(tcl);
        ResponseEntity<String> result = teamChoiceController.getAChoice();
        assertTrue(result.getBody().toString().contains("Tesla"));
        verify(teamChoiceService, times(1)).findAllTeamChoice();
    }
}