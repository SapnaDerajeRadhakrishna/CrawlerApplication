package org.demo.application.services;

import org.demo.application.exception.ResourceNotFoundException;
import org.demo.application.model.Result;

public interface CrawlerService {

	Result getResults() throws ResourceNotFoundException;

}
