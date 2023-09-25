package com.palo.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palo.team.entity.TeamChoice;

@Repository
public interface TeamChoiceRepository extends JpaRepository<TeamChoice, Integer> {
	
	

}
