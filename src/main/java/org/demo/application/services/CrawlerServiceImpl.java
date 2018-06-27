package org.demo.application.services;

import java.io.IOException;

import org.demo.application.exception.ResourceNotFoundException;
import org.demo.application.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	@Value("${crawler.source.json}")
	String sourceJson;

	@Autowired
	Crawler crawler;

	@Override
	public Result getResults() {
		// dummy
		Result result = new Result();
		try {
			result = crawler.getCrawlerCount(sourceJson);
		} catch (IOException e) {
			throw new ResourceNotFoundException();
		}
		return result;
	}

}
