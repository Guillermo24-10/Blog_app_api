package com.sistema.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.payloads.ApiResponse;
import com.sistema.blog.payloads.UserDto;
import com.sistema.blog.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST CREATE - USER
	@PostMapping(value = "/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);

		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// PUT - UPDATE USER
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable(name = "id") Integer id){
		UserDto updateUserDto = this.userService.updateUser(userDto, id);

		return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
	}

	// DELETE USER
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") Integer id) {
		this.userService.deleteUser(id);

		return new ResponseEntity<>(new ApiResponse("Usuario eliminado correctamente",true),HttpStatus.OK);
	}

	// GET - USER
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> userDto = this.userService.getAllUsers();

		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}

	@GetMapping(value = "/getByIdUser/{id}")
	public ResponseEntity<UserDto> getByIdUser(@PathVariable(name = "id")Integer id){
		UserDto userDto = this.userService.getUserById(id);

		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}

}
