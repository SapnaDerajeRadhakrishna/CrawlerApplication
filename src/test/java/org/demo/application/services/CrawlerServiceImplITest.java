package org.demo.application.services;

import static org.junit.Assert.assertEquals;

import org.demo.application.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerServiceImplITest {

	@Autowired
	CrawlerService crawlerService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetResults() throws Exception {

		Result testResult = crawlerService.getResults();

		assertEquals(testResult.getFailureCount(), 4);
		assertEquals(testResult.getSuccessCount(), 11);
		assertEquals(testResult.getTotalRequests(), 15);
	}
}
