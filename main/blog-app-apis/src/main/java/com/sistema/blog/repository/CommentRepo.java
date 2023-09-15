package com.sistema.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{


}
