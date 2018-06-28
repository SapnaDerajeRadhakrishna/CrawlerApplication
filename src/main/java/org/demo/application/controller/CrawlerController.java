package org.demo.application.controller;

import org.demo.application.exception.ResourceNotFoundException;
import org.demo.application.model.Result;
import org.demo.application.services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(CrawlerController.BASE_URL)
public class CrawlerController {

	public static final String BASE_URL = "/crawler/showResults";

	@Autowired
	private CrawlerService crawlerService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Result getResults() throws ResourceNotFoundException {
		log.debug("Invoking the results");
		Result results = crawlerService.getResults();
		log.info("Results retrieved are : total '{}', success '{}' , and failure '{}'", results.getTotalRequests(),
				results.getSuccessCount(), results.getFailureCount());
		return results;
	}
}
