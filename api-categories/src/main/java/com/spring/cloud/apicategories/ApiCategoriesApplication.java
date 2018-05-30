package com.spring.cloud.apicategories;

import com.spring.cloud.apicategories.pojo.Category;
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
public class ApiCategoriesApplication {

	private static Map<Integer,Category> categoriesMap = new HashMap<>();

	public static void main(String[] args) {
		categoriesMap.put(1, new Category(1, "JeuxVideos", "Categorie des jeux videos"));
		categoriesMap.put(2, new Category(2,"Informatique", "Informatique generale"));
		SpringApplication.run(ApiCategoriesApplication.class, args);
	}

	@RequestMapping("/")
	public Collection<Category> getAllCategories() {
		return categoriesMap.values();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category getAuthor(@PathVariable("id") int id) {
		return categoriesMap.get(id);
	}

}
