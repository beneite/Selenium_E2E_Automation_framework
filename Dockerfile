FROM bellsoft/liberica-openjdk-alpine:17.0.8

# workspace
WORKDIR /home/selenium-test

# environment variables: BROWSER, HUB_HOST_IP, TEST_SUTE, THREAD_COUNT

# add the required files
ADD target/docker-resources ./

# entrypoint (added \ to represnt next line so that readiblity can be improved)
ENTRYPOINT java -cp 'libs/*' \
            -Dselenium.grid.enabled=true \
            -Dbrowser=${BROWSER} \
            -Dselenium.grid.hubHost=${HUB_HOST_IP}  \
            org.testng.TestNG \
            -threadcount ${THREAD_COUNT} \
            test-suites/${TEST_SUTE}
