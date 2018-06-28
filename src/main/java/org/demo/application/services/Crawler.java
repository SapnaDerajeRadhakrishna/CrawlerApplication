package org.demo.application.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.demo.application.exception.ResourceNotFoundException;
import org.demo.application.model.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Crawler {

	public static Queue<String> queue = new LinkedList<>();
	public static Set<String> markedUrls = new HashSet<>();
	public static String regex = "(http[s]*:\\/\\/*httpbin.org\\/)(\\w+)\\/(\\w+)";
	public static Pattern pattern = Pattern.compile(regex);
	public static int successCount = 0;
	public static int failureCount = 0;
	public static int totalCount = 0;

	public Result getCrawlerCount(String rootUrl) throws ResourceNotFoundException, IOException {
		queue.add(rootUrl);
		BufferedReader bufferedReader = null;
		while (!queue.isEmpty()) {
			totalCount++;
			String crawledURL = queue.poll();
			log.debug("***The URL crawled is {}***", crawledURL);

			boolean ok = false;
			URL url = null;
			while (!ok) {
				try {
					url = new URL(crawledURL);
					bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
					successCount++;
					ok = true;
				} catch (IOException exception) {
					log.error("Recieved exception with URL '{}'", crawledURL);
					crawledURL = queue.poll();
					ok = false;
					totalCount++;
					failureCount++;
				}
			}
			StringBuilder builder = new StringBuilder();
			String tmp = null;
			while ((tmp = bufferedReader.readLine()) != null) {
				builder.append(tmp);
			}
			tmp = builder.toString();
			Matcher matcher = pattern.matcher(tmp);

			while (matcher.find()) {
				String listedURL = matcher.group();
				if (!markedUrls.contains(listedURL)) {
					markedUrls.add(listedURL);
					log.debug("***Site added for crawling '{}'***", listedURL);
					queue.add(listedURL);
				}
			}
		}
		if (bufferedReader != null) {
			bufferedReader.close();
		}

		Result result = new Result();
		result.setFailureCount(failureCount);
		result.setSuccessCount(successCount);
		result.setTotalRequests(totalCount);
		return result;
	}

	public void checkConnection(String rootUrl) throws ResourceNotFoundException {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(rootUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");
			int code = connection.getResponseCode();
			log.debug("Recived code '{}'", code);
			if (code != 200) {
				throw new ResourceNotFoundException("ROOT URL '{ " + rootUrl + " }' not accessible");
			}
		} catch (IOException exception) {
			throw new ResourceNotFoundException(exception.getMessage());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

}
