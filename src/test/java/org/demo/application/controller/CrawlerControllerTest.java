package org.demo.application.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.demo.application.exception.ResourceNotFoundException;
import org.demo.application.exception.RestResponseEntityExceptionHandler;
import org.demo.application.model.Result;
import org.demo.application.services.CrawlerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CrawlerControllerTest {

	@Mock
	CrawlerService crawlerService;

	@InjectMocks
	CrawlerController crawlerController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(crawlerController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
	}

	@Test
	public void testShowResults() throws Exception {
		Result result = new Result();
		result.setTotalRequests(5);
		result.setSuccessCount(4);
		result.setFailureCount(1);
		
		when(crawlerService.getResults()).thenReturn(result);
		
		mockMvc.perform(get(CrawlerController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.successCount", equalTo(4)))
				.andExpect(jsonPath("$.failureCount", equalTo(1)))
				.andExpect(jsonPath("$.totalRequests", equalTo(5)));
	}
	
	@Test
    public void testInvalidRootURL() throws Exception {

        when(crawlerService.getResults()).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CrawlerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
