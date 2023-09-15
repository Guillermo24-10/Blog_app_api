package com.sistema.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String resourceName;
	String fieldName;
	long fieldValie;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValie) {
		super(String.format("%s no encontrada con %s : '%s'", resourceName, fieldName, fieldValie));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValie = fieldValie;
	}

}
