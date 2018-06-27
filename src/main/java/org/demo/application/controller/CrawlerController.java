package org.demo.application.controller;

import org.demo.application.model.Result;
import org.demo.application.services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(CrawlerController.BASE_URL)
public class CrawlerController {

	public static final String BASE_URL = "/crawler";

	@Autowired
	private CrawlerService crawlerService;

	@GetMapping({ "", "/", "/index" })
	public String index() {
		return "index";
	}

	@PostMapping("/showResults")
	@ResponseStatus(HttpStatus.OK)
	public String getResults(Model model) {
		log.debug("Invoking the results");
		// invoke showResults
		Result results = crawlerService.getResults();
		log.debug("Results retrieved are : total '{}', success '{}' , and failure '{}'", results.getTotalRequests(),
				results.getSuccessCount(), results.getFailureCount());
		model.addAttribute("result", results);
		return "result";
	}
}
