package com.sistema.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.entities.Roles;

public interface RoleRepo extends JpaRepository<Roles, Integer>{

}
