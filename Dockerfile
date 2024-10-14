FROM bellsoft/liberica-openjdk-alpine:17.0.8

# installing curl and jq (curl for hitting selenium grid status endpoint and jq to query the json response)
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# environment variables: BROWSER, HUB_HOST_IP, TEST_SUTE, THREAD_COUNT

# add the required files
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

# entrypoint (added \ to represnt next line so that readiblity can be improved)
ENTRYPOINT sh runner.sh

