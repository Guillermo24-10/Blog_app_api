package com.sistema.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sistema.blog.config.AppConstants;
import com.sistema.blog.entities.Roles;
import com.sistema.blog.repository.RoleRepo;
@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {


	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		try {
				Roles role1 = new Roles();
				role1.setId(AppConstants.ADMIN_USER);
				role1.setRolName("ROLE_ADMIN");

				Roles roles2 = new Roles();
				roles2.setId(AppConstants.NORMAL_USER);
				roles2.setRolName("ROLE_NORMAL");

				List<Roles> roles = List.of(role1,roles2);
				List<Roles> result = this.roleRepo.saveAll(roles);

				result.forEach(x->{
					System.out.println(x.getRolName());
				});


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
