FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM tomcat:9-jdk17-openjdk
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]