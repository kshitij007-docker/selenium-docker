FROM bellsoft/liberica-openjdk-alpine:23.0.1

#Install curl and jq using apk in the above image
RUN apk add curl jq

#create workspace in above image.Here it will be in the home folder with name 'selenium-docker'
WORKDIR /home/selenium-docker

# Add the required files.Our all the files present in target/docker-resources2. Here we have used '.'
# 'docker-resources2' can be added in our current working directory i.e. /home/selenium-docker
ADD target/docker-resources2    ./
ADD runner.sh                   runner.sh
#Add runner.sh so that we can check if our selenium grid i.e. hub is ready before test execution starts.Lect 147


#Here we are adding runner.sh present in our local to our docker file with the same name

#Environment variables --These are the values which we want to provide in run time

#BROWSER

#hUB HOST :Which IP address you want to run

#Test suite: which testng.xml you want to run

# Thread count

# Build the image and run the test

# ENTRYPOINT java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=${HUB_HOST} -Dbrowser=${BROWSER} \
# -cp 'libs/*:selenium-dockerK-tests.zip:selenium-dockerK.jar' org.testng.TestNG -threadcount ${THREAD_COUNT} \
#  ${TEST_SUITE}

# Above entry point is commented because we have provided everything in the script

ENTRYPOINT sh runner.sh