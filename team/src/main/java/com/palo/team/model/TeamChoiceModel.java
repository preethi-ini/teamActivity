package com.palo.team.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamChoiceModel {
	
	@NotNull(message="this field is required")
	@Min(value = 1, message = "employeeId must be greater than or equal to 1")
	private Integer employeeId;
	
	@NotNull(message="this field is required")
	private String choice;

}
