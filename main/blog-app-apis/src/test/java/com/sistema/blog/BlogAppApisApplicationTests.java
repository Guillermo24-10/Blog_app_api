package com.sistema.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sistema.blog.repository.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	private void repoTest() {

		String classname = this.userRepo.getClass().getName();
		String packName = this.userRepo.getClass().getPackageName();

		System.out.println(classname);
		System.out.println(packName);
	}
}
