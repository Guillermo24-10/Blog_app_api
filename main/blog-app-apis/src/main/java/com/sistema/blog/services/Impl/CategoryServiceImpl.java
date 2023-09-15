package com.sistema.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.entities.Category;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.payloads.CategoryDto;
import com.sistema.blog.repository.CategoryRepo;
import com.sistema.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category add = this.categoryRepo.save(category);

		return this.modelMapper.map(add, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category findCat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		findCat.setTitle(categoryDto.getTitle());
		findCat.setDescription(categoryDto.getDescription());

		Category update = this.categoryRepo.save(findCat);

		return this.modelMapper.map(update, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category findCat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		return this.modelMapper.map(findCat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> findAll = this.categoryRepo.findAll();

		List<CategoryDto> categoryDtos = findAll.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return categoryDtos;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category findCat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		this.categoryRepo.delete(findCat);

	}

}
