package com.palo.team.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamChoiceModel {
	
	@NotNull(message="EmployeeId is required")
	@Min(value = 1, message = "EmployeeId must be greater than or equal to 1")
	@Max(value = 10000, message = "EmployeeId must be less than or equal to 10000")
	private int employeeId;
	
	@NotNull(message="Choice is required")
	@NotBlank(message="Choice cannot be blank")
	@Size(max=100, message ="Choice can be less than 100 characters" )
	private String choice;

}
