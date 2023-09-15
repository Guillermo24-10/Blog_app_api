package com.sistema.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

	private List<PostDto> content;
	private int numeroPagina;
	private int tamañoPagina;
	private long totalElementos;
	private int totalPaginas;

	private boolean ultimaPagina;
}
