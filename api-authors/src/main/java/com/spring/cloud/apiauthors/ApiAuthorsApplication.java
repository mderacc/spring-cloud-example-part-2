package com.spring.cloud.apiauthors;

import com.spring.cloud.apiauthors.pojo.Author;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ApiAuthorsApplication {

	private static Map<Integer, Author> authorsMap = new HashMap<>();

	public static void main(String[] args) {
		authorsMap.put(1, new Author(1, "Tim", "Cain"));
		authorsMap.put(2, new Author(2,"Leonard", "Boyarsky"));
		authorsMap.put(3, new Author(3,"Jason", "Anderson"));
		SpringApplication.run(ApiAuthorsApplication.class, args);
	}

	@RequestMapping("/")
	public Collection<Author> getAllNews() {
		return authorsMap.values();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Author getAuthor(@PathVariable("id") int id) {
		return authorsMap.get(id);
	}

}
