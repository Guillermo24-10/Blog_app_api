package com.sistema.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Integer id;

	@NotBlank
	@Size(min = 4,message = "el titulo debe contener minimo 4 caracteres")
	private String title;

	@NotBlank
	@Size(min = 10,message = "la descripcion debe contener minimo 10 caracteres")
	private String description;
}
