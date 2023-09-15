package com.sistema.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.blog.entities.Category;
import com.sistema.blog.entities.Post;
import com.sistema.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

	@Query(value = "select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param(value = "key") String title);
}
