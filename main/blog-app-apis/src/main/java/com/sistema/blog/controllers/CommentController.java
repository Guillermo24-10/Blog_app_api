package com.sistema.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.payloads.ApiResponse;
import com.sistema.blog.payloads.CommentDto;
import com.sistema.blog.services.CommentService;

@RestController
@RequestMapping(value = "api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping(value = "/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable(value = "postId")Integer id){

		CommentDto createComment = this.commentService.createComment(commentDto, id);

		return new ResponseEntity<>(createComment,HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable(value = "commentId")Integer id) {

		this.commentService.deleteComment(id);

		return new ResponseEntity<>(new ApiResponse("El comment fue eliminado correctamente!",true),HttpStatus.OK);
	}
}
