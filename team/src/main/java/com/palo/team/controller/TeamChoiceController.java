package com.palo.team.controller;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.palo.team.entity.TeamChoice;
import com.palo.team.model.TeamChoiceModel;
import com.palo.team.service.TeamChoiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@RestController

public class TeamChoiceController {
	
	@Autowired
	private TeamChoiceService teamChoiceService;
	
	private String selectedChoice = null;
	
	@Value(value = "${success.message}")
	private String successMsg;
	
	@Value(value = "${failure.message}")
	private String failureMsg;
	
	@Value(value = "${default.choice}")
	private String defaultChoice;
	
	
	@GetMapping("/")
	@Operation(summary = "Simple health check")
    @ApiResponses({
        @ApiResponse(responseCode = "200",description = "OK")
    })
	public ModelAndView firstPage() {
		log.info("Redirecting to home");
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}
	@GetMapping("/addChoice")
	public ModelAndView addChoicePage(Model model) {
		model.addAttribute("teamChoice", new TeamChoiceModel());
		ModelAndView mav = new ModelAndView("addChoice");
		return mav;
	}
	
	@PostMapping("/addNewChoice")
	@Operation(summary = "Add a new choice to the list of choices present in backend database")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Choice added successfully"),
	    @ApiResponse(responseCode = "400", description = "Bad Request"),
	    @ApiResponse(responseCode = "500", description = "Internal server error")
	})		
	public ResponseEntity<String> addChoice(
	        @RequestParam(name = "employeeId", required = true) int employeeId,
	        @RequestParam(name = "choice", required = true) String choice) {
		
	    TeamChoiceModel choiceModel = new TeamChoiceModel();
	    choiceModel.setEmployeeId(employeeId);
	    choiceModel.setChoice(choice);   
	    try {
	        getTeamChoiceService().addChoice(choiceModel);
	        setSelectedChoice(null);
	        return new ResponseEntity<String>(getSuccessMsg(), HttpStatus.OK);
	    } catch (Exception e) {
	    	log.error(e.getMessage());
	        return new ResponseEntity<String>(getFailureMsg(), HttpStatus.BAD_REQUEST);
	    }
	}
	@GetMapping(path = "/getChoice")
	@Operation(summary = "Get a random choice from the inputs received so far")
	@ApiResponses({
        @ApiResponse(responseCode = "200",description = "Choice fetched successfully"),
        @ApiResponse(responseCode = "400",description = "Bad Request"),
        @ApiResponse(responseCode = "500",description = "Internal server error")
    })
	public ResponseEntity<String> getAChoice(){
		 try {	
			if(null != getSelectedChoice() && !getSelectedChoice().isBlank()) {
				log.info("Choice generated already--"+getSelectedChoice());		
				return new ResponseEntity<String>(getSelectedChoice() +" <a href='/'>home<a>", HttpStatus.OK);
			}
			else {
				
				List<TeamChoice> choiceList = getTeamChoiceService().findAllTeamChoice();
				if (choiceList.size()==0) {
					setSelectedChoice(getDefaultChoice());
					return new ResponseEntity<String>(getSelectedChoice() +" <a href='/'>home<a>", HttpStatus.OK);
				}
				setSelectedChoice(choiceList.get(returnRandomNumber(choiceList.size())).getChoice());
				log.info("New Choice generated");		
				return new ResponseEntity<String>(getSelectedChoice() +" <a href='/'>home<a>", HttpStatus.OK);
			}
		 }
		 catch (Exception e) {
			 log.error(e.getMessage());
			 return new ResponseEntity<String>(getFailureMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private int returnRandomNumber(int bound) {
		
		Random random = new Random();
		int rand = 0;
		while (true){
		    rand = random.nextInt(bound);
		    if(rand>=0) break;
		}
		return rand;
	}
}
