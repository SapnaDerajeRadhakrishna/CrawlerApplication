package org.demo.application.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.demo.application.model.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Crawler {

	public static Queue<String> queue = new LinkedList<>();
	public static Set<String> marked = new HashSet<>();
	public static String regex = "(http[s]*:\\/\\/*httpbin.org\\/)(\\w+)\\/(\\w+)";
	public static int successCount = 0;
	public static int failureCount = 0;
	public static int totalCount = 0;

	public Result getCrawlerCount(String root) throws IOException {
		queue.add(root);
		// incrementing the total count by 1 as we are crawling the root element.
		BufferedReader bufferedReader = null;
		while (!queue.isEmpty()) {
			totalCount++;
			String crawledURL = queue.poll();
			log.debug("**********The URL crawled is {}", crawledURL);

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
			StringBuilder sb = new StringBuilder();
			String tmp = null;
			while ((tmp = bufferedReader.readLine()) != null) {
				sb.append(tmp);
			}
			tmp = sb.toString();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(tmp);

			while (matcher.find()) {
				String w = matcher.group();
				if (!marked.contains(w)) {
					marked.add(w);
					log.debug("Site added for crawling '{}'", w);
					queue.add(w);
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

}
