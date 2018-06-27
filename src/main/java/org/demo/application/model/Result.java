package org.demo.application.model;

import lombok.Data;

@Data
public class Result {

	private int successCount;
	private int failureCount;
	private int totalRequests;
}
