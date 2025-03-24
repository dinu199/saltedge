# UI and API Automation Framework

UI and API test automation full-featured based on Selenium WebDriver. It uses fluent page object pattern for UI testing. It uses Rest Template as 
primary library for sending API requests

## Getting Started

![Cucumber Logo](https://blog.mailsac.com/wp-content/uploads/2022/09/cucumber.png)

Cucumber is a tool that supports Behaviour-Driven Development (BDD).
Cucumber reads executable specifications written in plain text and validates that the software does what those specifications say. The specifications consist of multiple examples, or scenarios.
Each scenario is a list of steps for Cucumber to work through. Cucumber verifies that the software conforms with the specification and generates a report indicating ✅ success or ❌ failure for each scenario.

### Features

<ul>
    <li>READ ME documentation</li>
    <li>Cucumber reporting</li>
    <li>Cross browser(Edge, Chrome, Firefox, Safari) test running</li>
    <li>Cross Operating System test running</li>
    <li>Simple to read API requests</li>
    <li>Screenshot taking for every cucumber failed step located in cucumber report</li>
</ul>

### Requirements

1. OpenJDK 17
2. Maven 3.8.1
3. Cucumber 7.21.1
4. Junit 4.13.2

1. Build the project using maven
   ```mvn clean install```
2. Build the project using IntelliJ
   ```Main Menu -> Build -> Rebuild Project```
3. Run tests UI using
   ```mvn test``` ```UITestRunner.class```
4. Run tests API using
      ```mvn test``` ```APITestRunner.class```

### Reporting
In order to generate a cucumber report user has to use the ```TestRunner``` classes.
The report in generate in html format which user can open using any browser

#### Steps for generating cucumber report
1. Run tests using ```UITestRunner``` or ```APITestRunner```
2. Go to target package: ```target/extent-report.html```
3. Righ click on the file
4. Select ```Open In -> Browser```
5. Select your preferred browser

