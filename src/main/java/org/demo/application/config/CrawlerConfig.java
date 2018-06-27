package org.demo.application.config;

import org.demo.application.services.Crawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrawlerConfig {

	@Bean
	public Crawler getCrawler() {
		return new Crawler();
	}
}
