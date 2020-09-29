# Start with a base image containing Java runtime
FROM adoptopenjdk:11-jre-hotspot

# Make port 8100 available to the world outside this container
EXPOSE 8100
#
RUN apt-get update && apt-get install adduser 

RUN useradd -u 9001 -ms /bin/bash hmsadmin

USER hmsadmin

WORKDIR /home/access-manager

COPY ./target/*.jar ./app.jar

# Run the jar file 
ENTRYPOINT exec java $JAVA_OPTS -Dserver.port=8100 -jar app.jar
