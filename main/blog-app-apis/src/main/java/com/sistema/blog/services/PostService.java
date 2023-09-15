package com.sistema.blog.services;

import java.util.List;

import com.sistema.blog.payloads.PostDto;
import com.sistema.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	PostDto getPostById(Integer postId);

	//obtener todos los post por category
	List<PostDto> getPostByCategory(Integer categoryId);

	//obtener todos los post por user
	List<PostDto> getPostByUser(Integer userId);

	//buscar posts
	List<PostDto> searchPosts(String keyword);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

	void deletePost(Integer postId);
}
