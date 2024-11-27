# Application URLs

- [Flight Reservation](https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html)
- [Vendor Portal](https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html)

## Table of Contents
- [Project Architecture](#project-architecture)
- Run test Locally via testng from `src/test/resources/test-suites/flight-reservation-parallel-execution.xml`
- [How to run test from Target folder](#how-to-run-test-from-target-folder)
- [Running Tests from target using env variables](#running-tests-from-target-using-env-variables)

## Project Architecture
![projectArchitecture.png](src%2Ftest%2Fresources%2Fdocker%2FprojectArchitecture.png)

## How to Run Test:
1. Go to properties file and check the env variables.
2. use command to bring the docker container for **Elastic search** up: `docker run --name elasticSearchContainer -p 9200:9200 -it --memory 1GB -e "discovery.type=single-node" -e "xpack.security.enabled=false" docker.elastic.co/elasticsearch/elasticsearch:8.16.1`
3. 



## How to run test from Target folder

- Issue this command. `mvn clean package -DskipTests`. You should see `docker-resources` under `target`.
- `docker-resources` should contain following directories. Ensure that files are present under these directories.
    - config
    - libs
    - test-data
    - test-suites
- Go to `docker-resources` via command line. Then issue this command. One of them should work.
    - `java -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml`
    - `java -cp libs/* org.testng.TestNG test-suites/flight-reservation.xml`
    - `java -cp "libs/*" org.testng.TestNG test-suites/flight-reservation.xml`
- Windows Users
    - Try in PS
    - Try in regular command prompt. (`cmd`)
- Mac/Linux Users
    - Try via `bash`

## Running Tests from target using env variables

### TestNG parameters

- Go to `docker-resources` via command line. Then issue this command. One of them should work.

- Running a test suite

`java -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml`

- Running a test suite with specific thread count

`java -cp 'libs/*' org.testng.TestNG -threadcount 2 test-suites/flight-reservation.xml`

- TestNG by default creates **test-output** directory. You can change it with **-d** option.

`java -cp 'libs/*' org.testng.TestNG -threadcount 2 -d result test-suites/flight-reservation.xml`

---

### System Properties

- To pass the browser option

`java -Dbrowser=chrome -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml`

- To run the tests using Selenium Grid

`java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml`

- To run the tests using Selenium Grid with specific thread count

`java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml -threadcount 2`


### Need to add the below things:
- Log4j
- extent report