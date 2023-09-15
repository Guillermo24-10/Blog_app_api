package com.sistema.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;

	private String title;

	private String content;

	private String image;

	private Date created_date;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments = new HashSet<>();
}
