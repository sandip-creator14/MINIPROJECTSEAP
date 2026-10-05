# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q dependency:go-offline
COPY src ./src
RUN mvn -B -q clean package

# ---- Runtime stage ----
FROM tomcat:10.1-jdk17-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/shopping-app.war /usr/local/tomcat/webapps/shopping-app.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
