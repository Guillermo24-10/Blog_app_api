package com.sistema.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.blog.config.AppConstants;
import com.sistema.blog.payloads.ApiResponse;
import com.sistema.blog.payloads.PostDto;
import com.sistema.blog.payloads.PostResponse;
import com.sistema.blog.services.FileService;
import com.sistema.blog.services.PostService;

@RestController
@RequestMapping(value = "api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping(value = "/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable(value = "userId") Integer userId, @PathVariable(value = "categoryId") Integer categoryId) {

		PostDto dto = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	// get by user
	@GetMapping(value = "/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable(value = "userId") Integer id) {

		List<PostDto> postDtos = this.postService.getPostByUser(id);

		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

	// get by category

	@GetMapping(value = "/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(value = "categoryId") Integer id) {

		List<PostDto> postDtos = this.postService.getPostByCategory(id);

		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

	@GetMapping(value = "/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer number,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer size,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse postResponse = this.postService.getAllPost(number, size, sortBy, sortDir);

		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/posts/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(value = "id") Integer id) {

		PostDto postDtos = this.postService.getPostById(id);

		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

	@DeleteMapping(value = "/posts/deletePost/{id}")
	public ApiResponse deletePostId(@PathVariable(value = "id") Integer id) {
		this.postService.deletePost(id);

		return new ApiResponse("El post fue eliminado correctamente", true);
	}

	@PutMapping(value = "/posts/updatePost/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(value = "id") Integer id) {

		PostDto updatePost = this.postService.updatePost(postDto, id);

		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}

	// search posts
	@GetMapping(value = "/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostbyTitle(@PathVariable(value = "keywords") String keywords) {

		List<PostDto> result = this.postService.searchPosts(keywords);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// post image upload
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable(value = "postId") Integer id,
			@RequestParam(value = "image") MultipartFile image) throws IOException {

		PostDto postById = this.postService.getPostById(id);

		String fileName = this.fileService.uploadImage(path, image);
		postById.setImage(fileName);
		PostDto updatePost = this.postService.updatePost(postById, id);

		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}

	// method to serve files
	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable(value = "imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}
