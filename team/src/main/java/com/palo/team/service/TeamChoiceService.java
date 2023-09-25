package com.palo.team.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palo.team.entity.TeamChoice;
import com.palo.team.model.TeamChoiceModel;
import com.palo.team.repository.TeamChoiceRepository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Slf4j
public class TeamChoiceService {
	
	@Autowired
	private TeamChoiceRepository tcRepository;

	public void addChoice(TeamChoiceModel choice) {
		TeamChoice tc = new TeamChoice();
		BeanUtils.copyProperties(choice, tc);
		log.info(tc.toString());
		try {
			tcRepository.save(tc);
		}
		catch (Exception e) {

		}
	}
	public  List<TeamChoice> findAllTeamChoice() {
		return getTcRepository().findAll();
	}

}
