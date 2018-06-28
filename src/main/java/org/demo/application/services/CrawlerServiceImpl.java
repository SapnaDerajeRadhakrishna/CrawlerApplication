package org.demo.application.services;

import java.io.IOException;

import org.demo.application.exception.ResourceNotFoundException;
import org.demo.application.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrawlerServiceImpl implements CrawlerService {

	@Value("${crawler.root.url}")
	String rootUrl;

	@Autowired
	Crawler crawler;

	@Override
	public Result getResults() throws ResourceNotFoundException {
		// check if the root URL is accessible
		crawler.checkConnection(rootUrl);
		
		Result result = new Result();
		try {
			result = crawler.getCrawlerCount(rootUrl);
		} catch (IOException e) {
			log.error("IO Exception received ", e.getMessage());
			throw new ResourceNotFoundException(e.getMessage());
		}
		return result;
	}

}
