package com.dataj.j_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dataj.j_demo.model.UserEntidenty;
import com.dataj.j_demo.repository.UserRepository;

@SpringBootApplication
@Configuration
@RestController
public class JDemoApplication {

	@Value("${spring.application.name}")
	private static String name_application;

	private UserRepository userRepository;

	@Autowired
	public JDemoApplication(UserRepository new_UserRepository){
		this.userRepository=new_UserRepository;
	}

	@Bean
	public CommandLineRunner execute(){
		return args ->{
			UserEntidenty user = UserEntidenty.builder()
								.name("Samuel")
								.password("00")
								.cpf("65157826087")
								.email("samuel@gmail.com")
								.build();
			this.userRepository.save(user);
		};
	}

	@GetMapping("/list/{value1}/{value2}/Result")
	public String get_result(@PathVariable("value1") 
								Integer value1,
							 @PathVariable("value2")
								Integer value2
							){
			return String.valueOf((value1+value2));
	}

	public static void main(String[] args) {
		SpringApplication.run(JDemoApplication.class, args);
	}

}
