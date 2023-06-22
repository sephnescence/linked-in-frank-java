# Frank suggested using an older maven:3.6.3-openjdk-11-slim image, but my local maven is currently 3.9.2
#   I've been able to use these two images instead. So long as the Java versions were the same in both, it appears
#   to be fine
# You can run this in a docker container locally by first building
#   docker build -t learning-spring .
# Then running the container. There's no hot reloading available afaik, so build every time before running
#   docker run -p 8080:8080 learning-spring
# Note: There's an issue at the moment. The server responds to the root url, but not to anything else, and there are
#   no logs as to why
FROM maven:3.9.2-amazoncorretto-17 as BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package
COPY target/learning-spring-${VERSION}.jar target/application.jar

# I had tried this first, but it runs Java 8. I guess I got confused by the 17 in 3.17 being Java 17
#   8u372 apparently means 8.372. Noted
# FROM amazoncorretto:8u372-alpine3.17-jre

# I'll try this instead. I'm not sure what the difference is between headful and headless. The file size is
#   only a little bit bigger
FROM amazoncorretto:17.0.7-al2023-headful

WORKDIR /app/

COPY --from=BUILDER /build/target/application.jar /app/
CMD java -jar /app/application.jar