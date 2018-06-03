package com.spring.cloud.servicegateway;

import com.spring.cloud.servicegateway.filters.pre.FilterExample;
import java.net.MalformedURLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {
	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public FilterExample filterExample() {
		return new FilterExample();
	}
}
