**OpenCart UI Automation**

This project automates the UI testing of the OpenCart e-commerce platform using Selenium WebDriver with Java. It leverages TestNG for test execution, integrates reporting tools like Allure and ExtentReports, and supports parallel test execution using Selenium Grid.

**Tech Stack**

**Programming Language**: Java

**Automation Framework**: Selenium WebDriver

**Test Framework**: TestNG

**Build Tool**: Maven

**Reporting Tools**: Allure Reports, ExtentReports

**Logging**: Log4j

**Containerization & Grid**: Docker, Selenium Grid

**Prerequisites**

Make sure the following are installed:

Java JDK 11 or higher

Maven

Docker

IntelliJ/Eclipse (IDE)

Git


**Running Test Locally**

By default test will execute on chrome browser on windows machine to change the browser and os set environment variable

Steps to set the environment variables:
* export browser=edge
* export os=linux

* Run all test  **mvn test**
* Run a specific suite **mvn test -DsuiteXmlFile=testNGSmoke.xml**

**Parallel Execution with Selenium Grid**

**Standalone Setup (single machine) hub and node are on same machine**
Steps to run:
1. Go to selenium website and download selenium grid and place it somewhere in your system.
2. Go to selenium grid location and open cmd.
3. Run command to start selenium grid **java -jar selenium-server-4.29.0.jar standalone**.
4. URL to see session status **http://localhost:4444**.
5. Set environment variable **execution_mode=remote**.
6. Run command **mvn test** all test will get executed in parallel using selenium grid.

**Distributed Setup (multiple machines) using Docker**
Steps to run:
1. Start docker.
2. Run command **docker-compose up -d**
3. Set environment variable **execution_mode=remote**
3. Open another terminal and run command **mvn test**



