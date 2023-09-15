package com.sistema.blog.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Roles {

	@Id
	private Integer id;

	private String rolName;
}
