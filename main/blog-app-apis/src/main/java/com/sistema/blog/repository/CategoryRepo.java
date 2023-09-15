package com.sistema.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
