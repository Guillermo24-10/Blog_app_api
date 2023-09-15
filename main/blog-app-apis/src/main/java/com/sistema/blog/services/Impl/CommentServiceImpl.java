package com.sistema.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.entities.Comment;
import com.sistema.blog.entities.Post;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.payloads.CommentDto;
import com.sistema.blog.repository.CommentRepo;
import com.sistema.blog.repository.PostRepo;
import com.sistema.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post findById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		Comment comment = this.mapper.map(commentDto,Comment.class);
		comment.setPost(findById);

		Comment saveComment = this.commentRepo.save(comment);

		return this.mapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment findById = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepo.delete(findById);
	}

}
