# CrawlerApplication

[![CircleCI](https://circleci.com/gh/SapnaDerajeRadhakrishna/CrawlerApplication.svg?style=svg)](https://circleci.com/gh/SapnaDerajeRadhakrishna/CrawlerApplication)

[![codecov](https://codecov.io/gh/SapnaDerajeRadhakrishna/CrawlerApplication/branch/master/graph/badge.svg)](https://codecov.io/gh/SapnaDerajeRadhakrishna/CrawlerApplication)


## About 

The crawler application is built on <br/>

1. Gradle verion 4.3
2. Java8
3. Spring 5
4. SpringBoot2

PS: Did not use reactive programming


### Steps to bring up the application

In the parent folder

<code>
        ./gradlew build <br/>
        ./gradlew bootRun
</code>

### To see the results

In another terminal, trigger the below curl command

<code>
curl "http://localhost:8080//crawler/showResults"
</code>


### Sample Output

curl -i -X GET -H "Content-type: application/json" "http://localhost:8080/crawler/showResults" <br/><br/>

<code>
HTTP/1.1 200<br/>
Content-Type: application/json;charset=UTF-8<br/>
Transfer-Encoding: chunked<br/>
Date: Thu, 28 Jun 2018 00:45:29 GMT<br/> 
<br/>
{"successCount":12,"failureCount":4,"totalRequests":16}  <br/>
</code>
