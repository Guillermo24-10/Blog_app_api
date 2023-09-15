package com.sistema.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;

	@NotNull
	@NotEmpty(message = "El campo nombre no debe estar vacio")
	@Size(min = 4, message = "El campo nombre debe tener al menos 4 caracteres")
	private String name;

	@Email
	@NotEmpty(message = "El campo email no debe estar vacio")
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 3,max = 10,message = "El password debe contener minimo 3 caracteres, maximo 10 caracteres")
	private String password;

	@NotNull
	@NotEmpty(message = "El campo about no debe estar vacio")
	private String about;

	private Set<RoleDto> roles = new HashSet<>();
}
