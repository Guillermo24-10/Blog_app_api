package com.sistema.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.payloads.ApiResponse;
import com.sistema.blog.payloads.CategoryDto;
import com.sistema.blog.services.CategoryService;

@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	//CREATE
	@PostMapping(value = "/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto categoryDto2 =this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<>(categoryDto2,HttpStatus.CREATED);
	}

	//UPDATE
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable(value = "id") Integer id){
		CategoryDto categoryDto2 = this.categoryService.updateCategory(categoryDto, id);

		return new ResponseEntity<>(categoryDto2,HttpStatus.OK);
	}

	//DELETE
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(value = "id") Integer id) {
		this.categoryService.deleteCategory(id);

		return new ResponseEntity<>(new ApiResponse("Categoria eliminada correctamente",true),HttpStatus.OK);
	}

	//GET
	@GetMapping(value = "/getCategoryById/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(value = "id") Integer id){
		CategoryDto categoryDto = this.categoryService.getCategoryById(id);

		return new ResponseEntity<>(categoryDto,HttpStatus.OK);
	}

	//GET
		@GetMapping(value = "/getCategory")
		public ResponseEntity<List<CategoryDto>> getCategory(){
			List<CategoryDto> categoryDto = this.categoryService.getAllCategories();

			return new ResponseEntity<>(categoryDto,HttpStatus.OK);
		}
}
