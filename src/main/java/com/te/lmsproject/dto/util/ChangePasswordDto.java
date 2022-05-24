package com.te.lmsproject.dto.util;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
	@NotNull(message = "Employee Id is missing")
	@NotEmpty(message = "EMployee Id cannot be empty")
	private String employeeId;
	@NotNull(message = "existingPassword is missing")
	@NotEmpty(message = "existingPassword cannot be empty")
	private String existingPassword;
	@NotNull(message = "newPassword is missing")
	@NotEmpty(message = "newPassword cannot be empty")
	private String newPassword;
	@NotNull(message = "reTypeNewPassword is missing")
	@NotEmpty(message = "reTypeNewPassword cannot be empty")
	private String reTypeNewPassword;
	
}

