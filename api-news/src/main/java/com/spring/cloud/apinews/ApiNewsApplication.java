package com.spring.cloud.apinews;

import com.netflix.discovery.EurekaClient;
import com.spring.cloud.apinews.dto.NewsDto;
import com.spring.cloud.apinews.pojo.Author;
import com.spring.cloud.apinews.pojo.Category;
import com.spring.cloud.apinews.pojo.News;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ApiNewsApplication {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	private static Map<Integer,News> newsMap = new HashMap<>();

	public static void main(String[] args) {
		newsMap.put(1, new News(1, "Fallout 4", "Sortie de Fallout 4 en GOTY !",1, 1));
		newsMap.put(2, new News(2,"Java", "Sortie de Java 10",1, 2));
		newsMap.put(3, new News(3,"Soluce", "Soluce MassEffect",1, 1));
		SpringApplication.run(ApiNewsApplication.class, args);
	}

	@RequestMapping("/")
	public Collection<News> getAllNews() {
		return newsMap.values();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public NewsDto getNews(@PathVariable("id") int id){
		NewsDto newsDto = new NewsDto();
		if(newsMap.containsKey(id)) {
			News news = newsMap.get(id);
			String urlAuthorsApi = eurekaClient.getNextServerFromEureka("api-authors", false).getHomePageUrl();
			String urlCategoriesApi = eurekaClient.getNextServerFromEureka("api-categories", false).getHomePageUrl();
			Author author = restTemplate.getForObject(urlAuthorsApi + "/" + news.getAuthorId(), Author.class);
			Category category = restTemplate.getForObject(urlCategoriesApi + "/" + news.getCategoryId(), Category.class);
			newsDto.setDetail(news.getDetail());
			newsDto.setTitle(news.getTitle());
			newsDto.setFirstName(author.getFirstName());
			newsDto.setLastName(author.getLastName());
			newsDto.setCategory(category.getName());
		}
		return newsDto;
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
